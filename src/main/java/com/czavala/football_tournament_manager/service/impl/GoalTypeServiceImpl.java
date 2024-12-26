package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.goal.GoalTypeResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalTypeDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.GoalTypeMapper;
import com.czavala.football_tournament_manager.persistance.entity.GoalType;
import com.czavala.football_tournament_manager.persistance.repository.GoalTypeRepository;
import com.czavala.football_tournament_manager.service.GoalTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GoalTypeServiceImpl implements GoalTypeService {

    private final GoalTypeRepository goalTypeRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<GoalTypeResponseDto> findAll(Pageable pageable) {
        return goalTypeRepository.findAll(pageable)
                .map(goalType -> GoalTypeMapper.mapToGoalTypeDto(goalType));
    }

    @Transactional(readOnly = true) // indica que método es sólo de lectura a db
    @Override
    public GoalTypeResponseDto findById(Long goalTypeId) {
        GoalType goalType = this.findGoalTypeEntityById(goalTypeId);
        return GoalTypeMapper.mapToGoalTypeDto(goalType);
    }

    @Override
    public GoalTypeResponseDto create(SaveGoalTypeDto saveGoalTypeDto) {

        GoalType goalTypeEntity = GoalTypeMapper.mapToGoalTypeEntity(saveGoalTypeDto);
        goalTypeRepository.save(goalTypeEntity);

        return GoalTypeMapper.mapToGoalTypeDto(goalTypeEntity);
    }

    @Override
    public GoalTypeResponseDto updateById(Long goalTypeId, SaveGoalTypeDto saveGoalTypeDto) {

        GoalType goalTypeFromDB = this.findGoalTypeEntityById(goalTypeId);
        goalTypeFromDB.setTypeName(saveGoalTypeDto.getTypeName());
        goalTypeFromDB.setDescription(saveGoalTypeDto.getDescription());
        goalTypeFromDB.setActive(saveGoalTypeDto.isActive());
        goalTypeRepository.save(goalTypeFromDB);

        return GoalTypeMapper.mapToGoalTypeDto(goalTypeFromDB);
    }

    @Override
    public void disableById(Long goalTypeId) {
        GoalType goalTypeFromDB = this.findGoalTypeEntityById(goalTypeId);
        goalTypeFromDB.setActive(false);
        goalTypeRepository.save(goalTypeFromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public GoalType findGoalTypeEntityById(Long goalTypeId) {
        return goalTypeRepository.findById(goalTypeId)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de gol con ID " + goalTypeId + " no encontrado"));
    }
}
