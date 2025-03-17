package com.czavala.football_tournament_manager.mapper.match;

import com.czavala.football_tournament_manager.dto.match.CreatedMatchDto;
import com.czavala.football_tournament_manager.dto.match.MatchResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchDto;
import com.czavala.football_tournament_manager.persistance.entity.Match;

public class MatchMapper {


    public static MatchResponseDto mapToMatchResponseDto(Match matchEntity) {

        if (matchEntity == null) return null;

        return MatchResponseDto.builder()
                .id(matchEntity.getId())
                .matchDate(matchEntity.getMatchDate())
                .matchKickOff(matchEntity.getMatchKickOff())
                .matchday(matchEntity.getMatchday())
                .homeTeam(matchEntity.getHomeTeam().getName())
                .away_team(matchEntity.getAwayTeam().getName())
                .matchStatus(matchEntity.getMatchStatus().getStatusName())
                .homeTeamGoals(matchEntity.getHomeTeamGoals())
                .awayTeamGoals(matchEntity.getAwayTeamGoals())
                .notes(matchEntity.getNotes())
                .build();
    }

    public static CreatedMatchDto mapToCreatedMatchDto(Match matchEntity) {

        if (matchEntity == null) return null;

        return CreatedMatchDto.builder()
                .id(matchEntity.getId())
                .matchDate(matchEntity.getMatchDate())
                .matchKickOff(matchEntity.getMatchKickOff())
                .matchday(matchEntity.getMatchday())
                .homeTeamId(matchEntity.getHomeTeamId())
                .awayTeamId(matchEntity.getAwayTeamId())
                .matchStatusId(matchEntity.getMatchStatusId())
                .homeTeamGoals(matchEntity.getHomeTeamGoals())
                .awayTeamGoals(matchEntity.getAwayTeamGoals())
                .notes(matchEntity.getNotes())
                .build();
    }

}
