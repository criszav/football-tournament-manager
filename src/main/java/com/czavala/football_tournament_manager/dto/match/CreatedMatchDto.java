package com.czavala.football_tournament_manager.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class CreatedMatchDto {

    @JsonProperty("match_id")
    private Long id;

    @JsonProperty("match_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate matchDate;

    @JsonProperty("match_kick_off")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime matchKickOff;

    private Integer matchday;

    @JsonProperty("home_team_id")
    private Long homeTeamId;

    @JsonProperty("away_team_id")
    private Long awayTeamId;

    @JsonProperty("home_team_goals")
    private Integer homeTeamGoals;

    @JsonProperty("away_team_goals")
    private Integer awayTeamGoals;

    @JsonProperty("match_status_id")
    private Long matchStatusId;

    private String notes;

}
