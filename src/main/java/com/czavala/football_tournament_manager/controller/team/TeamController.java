package com.czavala.football_tournament_manager.controller.team;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.player.PlayerTeamDto;
import com.czavala.football_tournament_manager.dto.team.SaveTeamDto;
import com.czavala.football_tournament_manager.dto.team.TeamResponseDto;
import com.czavala.football_tournament_manager.service.TeamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<Page<TeamResponseDto>> findAllTeams(Pageable pageable) {
        Page<TeamResponseDto> teams = teamService.findAllTeams(pageable);
        return ResponseEntity.ok(teams);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> findOneById(@PathVariable Long teamId) {
        TeamResponseDto team = teamService.findOneById(teamId);
        return ResponseEntity.ok(team);
    }

    @PostMapping
    public ResponseEntity<TeamResponseDto> createOne(@RequestBody @Valid SaveTeamDto saveTeamDto,
                                                     HttpServletRequest request) {
        TeamResponseDto newTeam = teamService.createOne(saveTeamDto);

        String baseUrl = request.getRequestURL().toString();
        URI newTeamLocation = URI.create(baseUrl + "/" + newTeam.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newTeamLocation)
                .body(newTeam);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<TeamResponseDto> updateOneById(@PathVariable Long teamId,
                                                         @RequestBody @Valid SaveTeamDto saveTeamDto) {
        TeamResponseDto updatedTeam = teamService.updateOneById(teamId, saveTeamDto);
        return ResponseEntity.ok(updatedTeam);
    }

    @PutMapping("/{teamId}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable Long teamId) {
        teamService.disableOneById(teamId);
        return ResponseEntity.noContent().build();
    }

    @Transactional(readOnly = true)
    @GetMapping("/{teamId}/cards")
    public ResponseEntity<Page<CardTournamentResponseDto>> findAllCardsByTeam(@PathVariable Long teamId, Pageable pageable) {
        Page<CardTournamentResponseDto> cards = teamService.findAllCardsByTeam(teamId, pageable);
        return ResponseEntity.ok(cards);
    }

    @Transactional(readOnly = true)
    @GetMapping("/{teamId}/players")
    public ResponseEntity<Page<PlayerTeamDto>> findAllPlayersByTeam(@PathVariable Long teamId, Pageable pageable) {
        Page<PlayerTeamDto> players = teamService.findAllPlayers(teamId, pageable);
        return ResponseEntity.ok(players);
    }

}
