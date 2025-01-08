package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentStatusDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentStatusResponseDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.tournament.TournamentStatusMapper;
import com.czavala.football_tournament_manager.persistance.entity.TournamentStatus;
import com.czavala.football_tournament_manager.persistance.repository.TournamentStatusRepository;
import com.czavala.football_tournament_manager.service.TournamentStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class TournamentStatusServiceImpl implements TournamentStatusService {

    private final TournamentStatusRepository tournamentStatusRepository;

    public TournamentStatusServiceImpl(TournamentStatusRepository tournamentStatusRepository) {
        this.tournamentStatusRepository = tournamentStatusRepository;
    }

    @Override
    public Page<TournamentStatusResponseDto> findAll(Pageable pageable) {
        return tournamentStatusRepository.findAll(pageable)
                .map(TournamentStatusMapper::mapToTournamentStatusDto);
    }

    @Override
    public TournamentStatusResponseDto findById(Long id) {
        TournamentStatus tournamentStatus = this.findTournamentStatusEntityById(id);
        return TournamentStatusMapper.mapToTournamentStatusDto(tournamentStatus);
    }

    @Override
    public TournamentStatusResponseDto create(SaveTournamentStatusDto saveTournamentStatus) {
        TournamentStatus newTournamentStatus = TournamentStatusMapper.mapToTournamentStatusEntity(saveTournamentStatus);
        tournamentStatusRepository.save(newTournamentStatus);

        return TournamentStatusMapper.mapToTournamentStatusDto(newTournamentStatus);
    }

    @Override
    public TournamentStatusResponseDto updateById(Long id, SaveTournamentStatusDto saveTournamentStatus) {
        TournamentStatus tournamentStatusFromDB = this.findTournamentStatusEntityById(id);
        tournamentStatusFromDB.setStatusName(saveTournamentStatus.getStatusName());
        tournamentStatusFromDB.setDescription(saveTournamentStatus.getDescription());
        tournamentStatusFromDB.setActive(saveTournamentStatus.getIsActive());
        tournamentStatusRepository.save(tournamentStatusFromDB);

        return TournamentStatusMapper.mapToTournamentStatusDto(tournamentStatusFromDB);
    }

    @Override
    public void disableById(Long id) {
        TournamentStatus tournamentStatusFromDB = this.findTournamentStatusEntityById(id);
        tournamentStatusFromDB.setActive(false);
        tournamentStatusRepository.save(tournamentStatusFromDB);
    }

    @Override
    public TournamentStatus findTournamentStatusEntityById(Long id) {
        return tournamentStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tournament Status con ID " + id + " no encontrado"));
    }
}
