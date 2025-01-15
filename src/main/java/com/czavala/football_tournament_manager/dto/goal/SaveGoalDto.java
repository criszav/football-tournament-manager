package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveGoalDto implements Serializable {

    @Min(value = 1, message = "{generic.min}")
    @Max(value = 120, message = "{generic.max}")
    @NotNull(message = "{generic.notnull}")
    private Integer minute;

    @JsonProperty(value = "goal_type_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long goalTypeId;

    @JsonProperty(value = "match_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long matchId;

    @JsonProperty(value = "player_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long playerId;

    @JsonProperty(value = "team_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long teamId;

    @JsonProperty(value = "tournament_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long tournamentId;
}
