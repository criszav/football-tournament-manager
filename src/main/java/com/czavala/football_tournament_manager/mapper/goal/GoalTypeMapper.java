package com.czavala.football_tournament_manager.mapper.goal;

import com.czavala.football_tournament_manager.dto.goal.GoalTypeResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalTypeDto;
import com.czavala.football_tournament_manager.persistance.entity.GoalType;

public class GoalTypeMapper {

    public static GoalTypeResponseDto mapToGoalTypeDto(GoalType goalTypeEntity) {

        if (goalTypeEntity == null) return null;

        return GoalTypeResponseDto.builder()
                .id(goalTypeEntity.getId())
                .typeName(goalTypeEntity.getTypeName())
                .description(goalTypeEntity.getDescription())
                .isActive(goalTypeEntity.isActive())
                .build();

    }

    public static GoalType mapToGoalTypeEntity(SaveGoalTypeDto saveGoalTypeDto) {

        if (saveGoalTypeDto == null) return null;

        GoalType goalTypeEntity = new GoalType();
        goalTypeEntity.setTypeName(saveGoalTypeDto.getTypeName());
        goalTypeEntity.setDescription(saveGoalTypeDto.getDescription());
        goalTypeEntity.setActive(saveGoalTypeDto.getIsActive());

        return goalTypeEntity;
    }
}
