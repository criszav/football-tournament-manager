package com.czavala.football_tournament_manager.dto.goal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class GetGoalResponseDto implements Serializable {

    private Long id;

    @JsonProperty(value = "goal_minute")
    private Integer minute;

    @JsonProperty(value = "player_firstname")
    private String playerFirstname;

    @JsonProperty(value = "player_lastname")
    private String playerLastname;

    @JsonProperty(value = "goal_type")
    private String goalType;

    @JsonProperty("team_name")
    private String teamName;

    @JsonProperty("team_code")
    private String teamCode;

    private String tournament;
}
