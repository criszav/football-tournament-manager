package com.czavala.football_tournament_manager.controller.tournament;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.team.TeamTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Card;
import com.czavala.football_tournament_manager.persistance.entity.Team;
import com.czavala.football_tournament_manager.service.TournamentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping
    public ResponseEntity<Page<TournamentResponseDto>> findAllTournaments(Pageable pageable) {

        Page<TournamentResponseDto> tournaments = tournamentService.findAllTournaments(pageable);
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/active")
    public ResponseEntity<Page<TournamentResponseDto>> findAllActiveTournaments(Pageable pageable) {

        Page<TournamentResponseDto> tournaments = tournamentService.findAllActiveTournaments(pageable);
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponseDto> findOneById(@PathVariable Long tournamentId) {
        TournamentResponseDto tournament = tournamentService.findOneById(tournamentId);
        return ResponseEntity.ok(tournament);
    }

    @PostMapping
    public ResponseEntity<TournamentResponseDto> createOne(@RequestBody SaveTournamentDto saveTournament,
                                                           HttpServletRequest request) {

        TournamentResponseDto newTournament = tournamentService.createOne(saveTournament);

        String baseUrl = request.getRequestURL().toString();
        URI newTournamentLocation = URI.create(baseUrl + "/" + newTournament.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newTournamentLocation)
                .body(newTournament);
    }

    @PutMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponseDto> updateOneById(@PathVariable Long tournamentId,
                                                    @RequestBody SaveTournamentDto saveTournament) {
        TournamentResponseDto updatedTournament = tournamentService.updateOneById(tournamentId, saveTournament);
        return ResponseEntity.ok(updatedTournament);
    }

    @PutMapping("/{tournamentId}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable Long tournamentId) {
        tournamentService.disableOneById(tournamentId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{tournamentId}/teams")
    public ResponseEntity<Page<TeamTournamentResponseDto>> findAllTeamsByTournament(@PathVariable Long tournamentId, Pageable pageable) {
        Page<TeamTournamentResponseDto> teams = tournamentService.findAllTeamsByTournamentId(tournamentId, pageable);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{tournamentId}/cards")
    public ResponseEntity<Page<CardTournamentResponseDto>> findAllCardsByTournamentId(@PathVariable Long tournamentId, Pageable pageable) {
        Page<CardTournamentResponseDto> cards = tournamentService.findAllCardsByTournamentId(tournamentId, pageable);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{tournamentId}/cards/{teamId}")
    public ResponseEntity<Page<CardTournamentResponseDto>> findTeamCardsByTournamentId(@PathVariable Long tournamentId,
                                                                                       @PathVariable Long teamId,
                                                                                       Pageable pageable) {
        Page<CardTournamentResponseDto> cardsByTeam = tournamentService.findTeamCardsByTournamentId(tournamentId, teamId, pageable);
        return ResponseEntity.ok(cardsByTeam);
    }

    @GetMapping("/{tournamentId}/team-goals/{teamId}")
    public ResponseEntity<Page<GetGoalResponseDto>> findTeamGoalsByTournamentId(@PathVariable Long tournamentId,
                                                                                @PathVariable Long teamId,
                                                                                Pageable pageable) {
        Page<GetGoalResponseDto> goalsByTeam = tournamentService.findTeamGoalsByTournamentId(tournamentId, teamId, pageable);
        return ResponseEntity.ok(goalsByTeam);
    }

    @GetMapping("/{tournamentId}/goals")
    public ResponseEntity<Page<GetGoalResponseDto>> findGoalsByTournamentId(@PathVariable Long tournamentId,
                                                                            Pageable pageable) {
        Page<GetGoalResponseDto> goalsByTournament = tournamentService.findGoalsByTournamentId(tournamentId, pageable);
        return ResponseEntity.ok(goalsByTournament);
    }


    @GetMapping("/{tournamentId}/player-goals/{playerId}")
    public ResponseEntity<Page<GetGoalResponseDto>> findPlayerGoalsByTournamentId(@PathVariable Long tournamentId,
                                                                                  @PathVariable Long playerId,
                                                                                  Pageable pageable) {
        Page<GetGoalResponseDto> goalsByTournament = tournamentService.findPlayerGoalsByTournamentId(tournamentId, playerId, pageable);
        return ResponseEntity.ok(goalsByTournament);
    }

}
