package com.czavala.football_tournament_manager.mapper;

import com.czavala.football_tournament_manager.dto.tournament.SaveTournamentStatusDto;
import com.czavala.football_tournament_manager.dto.tournament.TournamentStatusResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.TournamentStatus;

public class TournamentStatusMapper {

    public static TournamentStatusResponseDto mapToTournamentStatusDto(TournamentStatus tournamentStatus) {

        if (tournamentStatus == null) return null;

        return TournamentStatusResponseDto.builder()
                .id(tournamentStatus.getId())
                .statusName(tournamentStatus.getStatusName())
                .description(tournamentStatus.getDescription())
                .isActive(tournamentStatus.isActive())
                .build();
    }

    public static TournamentStatus mapToTournamentStatusEntity(SaveTournamentStatusDto saveTournamentStatus) {

        if (saveTournamentStatus == null) return null;

        TournamentStatus tournamentStatus = new TournamentStatus();
        tournamentStatus.setStatusName(saveTournamentStatus.getStatusName());
        tournamentStatus.setDescription(saveTournamentStatus.getDescription());
        tournamentStatus.setActive(saveTournamentStatus.getIsActive());

        return tournamentStatus;
    }
}
