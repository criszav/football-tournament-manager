package com.czavala.football_tournament_manager.controller.tournament;

import com.czavala.football_tournament_manager.dto.tournament.AddedTeamDto;
import com.czavala.football_tournament_manager.service.TournamentTeamService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournament-teams")
public class TournamentTeamController {

    private final TournamentTeamService tournamentTeamService;

    public TournamentTeamController(TournamentTeamService tournamentTeamService) {
        this.tournamentTeamService = tournamentTeamService;
    }

    @PostMapping("/add-team")
    public ResponseEntity<AddedTeamDto> addTeamToTournament(@RequestParam Long tournamentId,
                                                            @RequestParam Long teamId) {

        AddedTeamDto addedTeam = tournamentTeamService.addTeamToTournament(tournamentId, teamId);
        return ResponseEntity.ok(addedTeam);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeTeamFromTournament(@PathVariable("id") Long tournamentTeamId) {
        tournamentTeamService.removeTeamFromTournament(tournamentTeamId);
        return ResponseEntity.status(HttpStatus.OK).body("Equipo ha sido removido del torneo exitosamente.");
    }

}
