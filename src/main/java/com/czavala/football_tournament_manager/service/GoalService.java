package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.goal.CreatedGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalDto;
import com.czavala.football_tournament_manager.persistance.entity.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoalService {
    
    Page<GetGoalResponseDto> findAll(Pageable pageable);


    GetGoalResponseDto findOneById(Long goalId);

    CreatedGoalResponseDto createOne(SaveGoalDto saveGoalDto);

    GetGoalResponseDto updateById(Long goalId, SaveGoalDto saveGoalDto);

    Goal findGoalEntityById(Long goalId);

    Page<GetGoalResponseDto> findGoalsByPlayerId(Long playerId, Pageable pageable);

    Page<GetGoalResponseDto> findPlayerGoalsByTeamId(Long playerId, Long teamId, Pageable pageable);
}
