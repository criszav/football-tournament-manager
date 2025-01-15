package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.goal.CreatedGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.goal.GoalMapper;
import com.czavala.football_tournament_manager.persistance.entity.Goal;
import com.czavala.football_tournament_manager.persistance.repository.GoalRepository;
import com.czavala.football_tournament_manager.service.GoalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }


    @Override
    public Page<GetGoalResponseDto> findAll(Pageable pageable) {
        return goalRepository.findAll(pageable)
                .map(GoalMapper::mapToGoalDto);
    }

    @Override
    public GetGoalResponseDto findOneById(Long goalId) {
        return GoalMapper.mapToGoalDto(this.findGoalEntityById(goalId));
    }

    @Override
    public CreatedGoalResponseDto createOne(SaveGoalDto saveGoalDto) {
        Goal goal = GoalMapper.mapToGoalEntity(saveGoalDto);
        goalRepository.save(goal);
        return GoalMapper.mapToCreatedGoalDto(goal);
    }

    @Override
    public GetGoalResponseDto updateById(Long goalId, SaveGoalDto saveGoalDto) {
        Goal goalFromDB = this.findGoalEntityById(goalId);
        goalFromDB.setMinute(saveGoalDto.getMinute());
        goalFromDB.setGoalTypeId(saveGoalDto.getGoalTypeId());
        goalFromDB.setPlayerId(saveGoalDto.getPlayerId());
        goalFromDB.setTeamId(saveGoalDto.getTeamId());
        goalFromDB.setMatchId(saveGoalDto.getMatchId());
        goalFromDB.setTournamentId(saveGoalDto.getTournamentId());
        goalRepository.save(goalFromDB);

        return GoalMapper.mapToGoalDto(goalFromDB);
    }

    @Override
    public Goal findGoalEntityById(Long goalId) {
        return goalRepository.findById(goalId)
                .orElseThrow(() -> new ResourceNotFoundException("Goal con ID " + goalId + " no encontrado"));
    }

    @Override
    public Page<GetGoalResponseDto> findGoalsByPlayerId(Long playerId, Pageable pageable) {
        return goalRepository.findByPlayerId(playerId, pageable)
                .map(GoalMapper::mapToGoalDto);
    }

    @Override
    public Page<GetGoalResponseDto> findPlayerGoalsByTeamId(Long playerId, Long teamId, Pageable pageable) {
        return goalRepository.findByPlayerIdAndTeamId(playerId, teamId, pageable)
                .map(GoalMapper::mapToGoalDto);
    }
}
