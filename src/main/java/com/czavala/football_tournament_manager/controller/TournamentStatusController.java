package com.czavala.football_tournament_manager.controller;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentStatusDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentStatusResponseDto;
import com.czavala.football_tournament_manager.service.TournamentStatusService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;


@RestController
@RequestMapping("/tournament-status")
public class TournamentStatusController {

    private final TournamentStatusService tournamentStatusService;

    public TournamentStatusController(TournamentStatusService tournamentStatusService) {
        this.tournamentStatusService = tournamentStatusService;
    }

    @GetMapping
    public ResponseEntity<Page<TournamentStatusResponseDto>> findAll(Pageable pageable) {
        Page<TournamentStatusResponseDto> tournamentStatusList = tournamentStatusService.findAll(pageable);
        return ResponseEntity.ok(tournamentStatusList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentStatusResponseDto> findOneById(@PathVariable Long id) {
        TournamentStatusResponseDto tournamentStatus = tournamentStatusService.findById(id);
        return ResponseEntity.ok(tournamentStatus);
    }

    @PostMapping
    public ResponseEntity<TournamentStatusResponseDto> createOne(@RequestBody @Valid SaveTournamentStatusDto saveTournamentStatus,
                                                                 HttpServletRequest request) {

        TournamentStatusResponseDto newTournamentStatus = tournamentStatusService.create(saveTournamentStatus);

        // Agrega Header 'Location' con URI del nuevo recurso creado
        String baseUrl = request.getRequestURL().toString();
        URI newTournamentStatusLocation = URI.create(baseUrl + "/" + newTournamentStatus.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newTournamentStatusLocation)
                .body(newTournamentStatus);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentStatusResponseDto> updateOneById(@PathVariable Long id,
                                                                     @RequestBody @Valid SaveTournamentStatusDto saveTournamentStatus) {
        TournamentStatusResponseDto updatedTournamentStatus = tournamentStatusService.updateById(id, saveTournamentStatus);
        return ResponseEntity.ok(updatedTournamentStatus);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Void> disableOneById(@PathVariable Long id) {
        tournamentStatusService.disableById(id);
        return ResponseEntity.noContent().build();
    }

}
