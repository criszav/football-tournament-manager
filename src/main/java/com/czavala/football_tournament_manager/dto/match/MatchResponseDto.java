package com.czavala.football_tournament_manager.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchResponseDto {

    @JsonProperty("match_id")
    private Long id;

    @JsonProperty("match_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate matchDate;

    @JsonProperty("match_kick_off")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime matchKickOff;

    private Integer matchday;

    @JsonProperty("home_team")
    private String homeTeam;

    @JsonProperty("away_team")
    private String away_team;

    @JsonProperty("home_team_goals")
    private Integer homeTeamGoals;

    @JsonProperty("away_team_goals")
    private Integer awayTeamGoals;

    @JsonProperty("match_status")
    private String matchStatus;

    private String notes;

}
