package com.czavala.football_tournament_manager.mapper.stats;

import com.czavala.football_tournament_manager.dto.stats.ScorerDto;
import com.czavala.football_tournament_manager.persistance.entity.Goal;

public class StatsMapper {

    public static ScorerDto mapToScorerDto(Goal goal) {

        if (goal == null) return null;

        int goals = 0;

        return ScorerDto.builder()
                .playerFirstname(goal.getPlayer().getFirstname())
                .playerLastname(goal.getPlayer().getLastname())
                .team(goal.getTeam().getName())
                .goals(goals)
                .build();

    }

}
