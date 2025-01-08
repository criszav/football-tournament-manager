package com.czavala.football_tournament_manager.mapper.match;

import com.czavala.football_tournament_manager.dto.match.MatchStatusResponseDto;
import com.czavala.football_tournament_manager.dto.match.SaveMatchStatusDto;
import com.czavala.football_tournament_manager.persistance.entity.MatchStatus;

public class MatchStatusMapper {

    public static MatchStatusResponseDto mapToMatchStatusDto(MatchStatus matchStatus) {

        if (matchStatus == null) return null;

        return MatchStatusResponseDto.builder()
                .id(matchStatus.getId())
                .statusName(matchStatus.getStatusName())
                .description(matchStatus.getDescription())
                .isActive(matchStatus.isActive())
                .build();
    }

    public static MatchStatus mapToMatchStatusEntity(SaveMatchStatusDto matchStatusDto) {

        if (matchStatusDto == null) return null;

        MatchStatus matchStatus = new MatchStatus();
        matchStatus.setStatusName(matchStatusDto.getStatusName());
        matchStatus.setDescription(matchStatusDto.getDescription());
        matchStatus.setActive(matchStatusDto.getIsActive());

        return matchStatus;

    }
}
