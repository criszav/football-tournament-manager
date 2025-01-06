package com.czavala.football_tournament_manager.controller;

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

    @GetMapping("/{tournamentId}")
    public ResponseEntity<TournamentResponseDto> findOneById(@PathVariable Long tournamentId) {
        TournamentResponseDto tournament = tournamentService.findOneById(tournamentId);
        return ResponseEntity.ok(tournament);
    }

    @GetMapping("/{tournamentId}/teams")
    public ResponseEntity<List<Team>> findAllTeamsByTournament(@PathVariable Long tournamentId) {
        List<Team> teams = tournamentService.findAllTeamsByTournamentId(tournamentId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{tournamentId}/cards")
    public ResponseEntity<List<Card>> findAllCardsByTournamentId(@PathVariable Long tournamentId) {
        List<Card> cards = tournamentService.findAllCardsByTournamentId(tournamentId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{tournamentId}/cards/{teamId}")
    public ResponseEntity<List<Card>> findTeamCardsByTournamentId(@PathVariable Long tournamentId,
                                                                  @PathVariable Long teamId) {
        List<Card> cardsByTeam = tournamentService.findTeamCardsByTournamentId(tournamentId, teamId);
        return ResponseEntity.ok(cardsByTeam);
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

}
