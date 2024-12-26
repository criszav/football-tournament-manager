package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.goal.GoalTypeResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalTypeDto;
import com.czavala.football_tournament_manager.persistance.entity.GoalType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GoalTypeService {

    Page<GoalTypeResponseDto> findAll(Pageable pageable);

    GoalTypeResponseDto findById(Long goalTypeId);

    GoalTypeResponseDto create(SaveGoalTypeDto saveGoalTypeDto);

    GoalTypeResponseDto updateById(Long goalTypeId, SaveGoalTypeDto goalType);

    void disableById(Long goalTypeId);

    GoalType findGoalTypeEntityById(Long goalTypeId);
}
