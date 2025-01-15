package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatedGoalResponseDto {

    private Long id;

    @JsonProperty("goal_minute")
    private Integer goalMinute;

    @JsonProperty("goal_type_id")
    private Long goalTypeId;

    @JsonProperty("player_id")
    private Long playerId;

    @JsonProperty("match_id")
    private Long matchId;

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty("tournament_id")
    private Long tournamentId;
}
