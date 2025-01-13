package com.czavala.football_tournament_manager.mapper.team;

import com.czavala.football_tournament_manager.dto.team.SaveTeamDto;
import com.czavala.football_tournament_manager.dto.team.TeamResponseDto;
import com.czavala.football_tournament_manager.dto.team.TeamTournamentResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Team;

public class TeamMapper {

    public static TeamTournamentResponseDto mapToTeamTournamentDto(Team teamEntity) {

        if (teamEntity == null) return null;

        return TeamTournamentResponseDto.builder()
                .teamName(teamEntity.getName())
                .teamCode(teamEntity.getTeamCode())
                .imageUrl(teamEntity.getImageUrl())
                .build();
    }

    public static TeamResponseDto mapToTeamResponseDto(Team teamEntity) {

        if (teamEntity == null) return null;

        return TeamResponseDto.builder()
                .id(teamEntity.getId())
                .teamName(teamEntity.getName())
                .teamCode(teamEntity.getTeamCode())
                .imageUrl(teamEntity.getImageUrl())
                .isActive(teamEntity.isActive())
                .build();
    }

    public static Team mapToTeamEntity(SaveTeamDto saveTeamDto) {

        if (saveTeamDto == null) return null;

        Team team = new Team();
        team.setName(saveTeamDto.getName());
        team.setTeamCode(saveTeamDto.getTeamCode());
        team.setImageUrl(saveTeamDto.getImageUrl());
        team.setActive(saveTeamDto.getIsActive());
        team.setEnabled(saveTeamDto.getIsEnabled());
        team.setUserId(saveTeamDto.getUserId());

        return team;
    }

}
