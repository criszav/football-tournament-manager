package com.czavala.football_tournament_manager.controller;

import com.czavala.football_tournament_manager.dto.stats.*;
import com.czavala.football_tournament_manager.service.StatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/scorers/{tournamentId}")
    public ResponseEntity<List<ScorerDto>> findScorersByTournament(@PathVariable Long tournamentId) {
        List<ScorerDto> scorers = statsService.getScorersByTournament(tournamentId);
        return ResponseEntity.ok(scorers);
    }

    @GetMapping("/clubs/goals/{tournamentId}")
    public ResponseEntity<List<TeamGoalsScoredDto>> findGoalsScoredByTeamByTournament(@PathVariable Long tournamentId) {
        List<TeamGoalsScoredDto> teams = statsService.getGoalsScoredByTeam(tournamentId);
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/players/cards/red/{tournamentId}")
    public ResponseEntity<List<RedCardListByTournamentDto>> findRedCardListByTournament(@PathVariable Long tournamentId) {
        List<RedCardListByTournamentDto> redCards = statsService.getRedCardsByTournament(tournamentId);
        return ResponseEntity.ok(redCards);
    }

    @GetMapping("/players/cards/yellow/{tournamentId}")
    public ResponseEntity<List<YellowCardListByTournamentDto>> findYellowCardListByTournament(@PathVariable Long tournamentId) {
        List<YellowCardListByTournamentDto> redCards = statsService.getYellowCardsByTournament(tournamentId);
        return ResponseEntity.ok(redCards);
    }

    @GetMapping("/clubs/cards/red/{tournamentId}")
    public ResponseEntity<List<TeamRedCardsDto>> findRedCardsByTeamByTournament(@PathVariable Long tournamentId) {
        List<TeamRedCardsDto> teamRedCards = statsService.getRedCardListByTeam(tournamentId);
        return ResponseEntity.ok(teamRedCards);
    }

    @GetMapping("/clubs/cards/yellow/{tournamentId}")
    public ResponseEntity<List<TeamYellowCardsDto>> findYellowCardsByTeamByTournament(@PathVariable Long tournamentId) {
        List<TeamYellowCardsDto> teamYellowCards = statsService.getYellowCardListByTeam(tournamentId);
        return ResponseEntity.ok(teamYellowCards);
    }

}
