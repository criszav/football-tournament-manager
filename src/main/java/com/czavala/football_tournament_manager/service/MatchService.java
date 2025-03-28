package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.match.CreatedMatchDto;
import com.czavala.football_tournament_manager.dto.match.MatchResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchService {
    
    Page<MatchResponseDto> findAllMatches(Pageable pageable);

    MatchResponseDto findOneById(Long matchId);

    CreatedMatchDto create(SaveMatchDto matchDto);

    MatchResponseDto updateById(Long matchId, SaveMatchDto matchDto);

    Page<MatchResponseDto> findMatchesByMatchStatusId(Long matchStatusId, Pageable pageable);

    List<MatchResponseDto> findMatchesByTeamInTournament(Long tournamentId, Long teamId);

    List<MatchResponseDto> findByMatchdayInTournament(Long tournamentId, Integer matchday);
}

