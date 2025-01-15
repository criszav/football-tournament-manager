package com.czavala.football_tournament_manager.mapper.goal;

import com.czavala.football_tournament_manager.dto.goal.CreatedGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.GetGoalResponseDto;
import com.czavala.football_tournament_manager.dto.goal.SaveGoalDto;
import com.czavala.football_tournament_manager.persistance.entity.Goal;

public class GoalMapper {


    public static GetGoalResponseDto mapToGoalDto(Goal goalEntity) {

        if (goalEntity == null) return null;

        return GetGoalResponseDto.builder()
                .id(goalEntity.getId())
                .minute(goalEntity.getMinute())
                .goalType(goalEntity.getGoalType().getTypeName())
                .playerFirstname(goalEntity.getPlayer().getFirstname())
                .playerLastname(goalEntity.getPlayer().getLastname())
                .teamName(goalEntity.getTeam().getName())
                .teamCode(goalEntity.getTeam().getTeamCode())
                .tournament(goalEntity.getTournament().getName())
                .build();

    }

    public static CreatedGoalResponseDto mapToCreatedGoalDto(Goal goalEntity) {

        if (goalEntity == null) return null;

        return CreatedGoalResponseDto.builder()
                .id(goalEntity.getId())
                .goalMinute(goalEntity.getMinute())
                .goalTypeId(goalEntity.getGoalTypeId())
                .playerId(goalEntity.getPlayerId())
                .teamId(goalEntity.getTeamId())
                .matchId(goalEntity.getMatchId())
                .tournamentId(goalEntity.getTournamentId())
                .build();

    }


    public static Goal mapToGoalEntity(SaveGoalDto goalDto) {

        if (goalDto == null) return null;

        Goal goal = new Goal();
        goal.setMinute(goalDto.getMinute());
        goal.setGoalTypeId(goalDto.getGoalTypeId());
        goal.setPlayerId(goalDto.getPlayerId());
        goal.setMatchId(goalDto.getMatchId());
        goal.setTeamId(goalDto.getTeamId());
        goal.setTournamentId(goalDto.getTournamentId());

        return goal;
    }
}
