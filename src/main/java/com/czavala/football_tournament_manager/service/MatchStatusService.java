package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.match.MatchStatusResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchStatusDto;
import com.czavala.football_tournament_manager.persistance.entity.MatchStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MatchStatusService {

    Page<MatchStatusResponseDto> findAll(Pageable pageable);

    MatchStatusResponseDto findById(Long id);

    MatchStatusResponseDto create(SaveMatchStatusDto saveMatchStatus);

    MatchStatusResponseDto updateById(Long id, SaveMatchStatusDto saveMatchStatus);

    void disableById(Long id);

    MatchStatus findMatchStatusEntityById(Long id);
}
