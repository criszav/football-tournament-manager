package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentStatusDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentStatusResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.TournamentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TournamentStatusService {

    Page<TournamentStatusResponseDto> findAll(Pageable pageable);

    TournamentStatusResponseDto findById(Long id);

    TournamentStatusResponseDto create(SaveTournamentStatusDto saveTournamentStatus);

    TournamentStatusResponseDto updateById(Long id, SaveTournamentStatusDto saveTournamentStatus);

    void disableById(Long id);

    TournamentStatus findTournamentStatusEntityById(Long id);
}
