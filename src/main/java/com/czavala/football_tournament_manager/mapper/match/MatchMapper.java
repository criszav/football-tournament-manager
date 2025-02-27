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
                .homeTeam(matchEntity.getHomeTeam().getName())
                .away_team(matchEntity.getAwayTeam().getName())
                .matchStatus(matchEntity.getMatchStatus().getStatusName())
                .homeTeamGoals(matchEntity.getHomeTeamGoals())
                .awayTeamGoals(matchEntity.getAwayTeamGoals())
                .build();
    }

    public static CreatedMatchDto mapToCreatedMatchDto(Match matchEntity) {

        if (matchEntity == null) return null;

        return CreatedMatchDto.builder()
                .id(matchEntity.getId())
                .matchDate(matchEntity.getMatchDate())
                .matchKickOff(matchEntity.getMatchKickOff())
                .homeTeamId(matchEntity.getHomeTeamId())
                .awayTeamId(matchEntity.getAwayTeamId())
                .matchStatusId(matchEntity.getMatchStatusId())
                .homeTeamGoals(matchEntity.getHomeTeamGoals())
                .awayTeamGoals(matchEntity.getAwayTeamGoals())
                .build();
    }


    public static Match mapToMatchEntity(SaveMatchDto matchDto) {

        if (matchDto == null) return null;

        Match match = new Match();
        match.setMatchDate(matchDto.getMatchDate());
        match.setMatchKickOff(matchDto.getMatchKickOff());
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setMatchStatusId(matchDto.getMatchStatusId());
        match.setHomeTeamGoals(matchDto.getHomeTeamGoals());
        match.setAwayTeamGoals(matchDto.getAwayTeamGoals());
        match.setTournamentId(matchDto.getTournamentId());

        return match;

    }

}
