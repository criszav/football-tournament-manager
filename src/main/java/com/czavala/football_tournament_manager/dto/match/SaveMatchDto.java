package com.czavala.football_tournament_manager.dto.match;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Getter
@Setter
public class SaveMatchDto implements Serializable {

    @JsonProperty("match_date")
    private Date matchDate;

    @JsonProperty("match_kick_off")
    private LocalTime matchKickOff;

    @Positive(message = "{generic.positive}")
    private Integer matchday;

    @JsonProperty("home_team_goals")
    @PositiveOrZero(message = "{generic.positive-or-zero")
    private Integer homeTeamGoals;

    @JsonProperty("away_team_goals")
    @PositiveOrZero(message = "{generic.positive-or-zero")
    private Integer awayTeamGoals;

    @JsonProperty("home_team_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long homeTeamId;

    @JsonProperty("away_team_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long awayTeamId;

    @JsonProperty("match_status_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long matchStatusId;

    @JsonProperty("tournament_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long tournamentId;

    private String notes;

}
