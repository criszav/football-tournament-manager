package com.czavala.football_tournament_manager.mapper;

import com.czavala.football_tournament_manager.dto.goal.GoalTypeResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalTypeDto;
import com.czavala.football_tournament_manager.persistance.entity.GoalType;

public class GoalTypeMapper {

    public static GoalTypeResponseDto mapToGoalTypeDto(GoalType goalTypeEntity) {

        if (goalTypeEntity == null) return null;

        GoalTypeResponseDto goalTypeResponseDto = new GoalTypeResponseDto();
        goalTypeResponseDto.setId(goalTypeEntity.getId());
        goalTypeResponseDto.setTypeName(goalTypeEntity.getTypeName());
        goalTypeResponseDto.setDescription(goalTypeEntity.getDescription());
        goalTypeResponseDto.setActive(goalTypeEntity.isActive());

        return goalTypeResponseDto;
    }

    public static GoalType mapToGoalTypeEntity(SaveGoalTypeDto saveGoalTypeDto) {

        if (saveGoalTypeDto == null) return null;

        GoalType goalTypeEntity = new GoalType();
        goalTypeEntity.setTypeName(saveGoalTypeDto.getTypeName());
        goalTypeEntity.setDescription(saveGoalTypeDto.getDescription());
        goalTypeEntity.setActive(saveGoalTypeDto.isActive());

        return goalTypeEntity;
    }
}
