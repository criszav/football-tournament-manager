package com.czavala.football_tournament_manager.mapper.tournament;

import com.czavala.football_tournament_manager.dto.tournament.AddedTeamDto;
import com.czavala.football_tournament_manager.persistance.entity.TournamentTeam;

public class TournamentTeamMapper {

    public static AddedTeamDto mapToAddedTeamDto(TournamentTeam tournamentTeamEntity) {

        if (tournamentTeamEntity == null) return null;

        return AddedTeamDto.builder()
                .tournamentId(tournamentTeamEntity.getTournamentId())
                .teamId(tournamentTeamEntity.getTeamId())
                .createdAt(tournamentTeamEntity.getCreatedAt())
                .build();

    }
}
