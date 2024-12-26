package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.match.MatchStatusResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchStatusDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.MatchStatusMapper;
import com.czavala.football_tournament_manager.persistance.entity.MatchStatus;
import com.czavala.football_tournament_manager.persistance.repository.MatchStatusRepository;
import com.czavala.football_tournament_manager.service.MatchStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MatchStatusServiceImpl implements MatchStatusService {

    private final MatchStatusRepository matchStatusRepository;

    public MatchStatusServiceImpl(MatchStatusRepository matchStatusRepository) {
        this.matchStatusRepository = matchStatusRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MatchStatusResponseDto> findAll(Pageable pageable) {
        return matchStatusRepository.findAll(pageable)
                .map(MatchStatusMapper::mapToMatchStatusDto);
    }

    @Transactional(readOnly = true)
    @Override
    public MatchStatusResponseDto findById(Long id) {
        MatchStatus matchStatus = this.findMatchStatusEntityById(id);
        return MatchStatusMapper.mapToMatchStatusDto(matchStatus);
    }

    @Override
    public MatchStatusResponseDto create(SaveMatchStatusDto saveMatchStatus) {
        MatchStatus newMatchStatus = MatchStatusMapper.mapToMatchStatusEntity(saveMatchStatus);
        matchStatusRepository.save(newMatchStatus);

        return MatchStatusMapper.mapToMatchStatusDto(newMatchStatus);
    }

    @Override
    public MatchStatusResponseDto updateById(Long id, SaveMatchStatusDto saveMatchStatus) {
        MatchStatus matchStatusFromDB = this.findMatchStatusEntityById(id);
        matchStatusFromDB.setStatusName(saveMatchStatus.getStatusName());
        matchStatusFromDB.setDescription(saveMatchStatus.getDescription());
        matchStatusFromDB.setActive(saveMatchStatus.getIsActive());
        matchStatusRepository.save(matchStatusFromDB);

        return MatchStatusMapper.mapToMatchStatusDto(matchStatusFromDB);
    }

    @Override
    public void disableById(Long id) {
        MatchStatus matchStatusFromDB = this.findMatchStatusEntityById(id);
        matchStatusFromDB.setActive(false);
        matchStatusRepository.save(matchStatusFromDB);
    }

    @Override
    public MatchStatus findMatchStatusEntityById(Long id) {
        return matchStatusRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match Status con ID " + id + " no encontrado"));
    }
}
