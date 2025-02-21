package com.czavala.football_tournament_manager.dto.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ScorerDto {

    @JsonProperty("player_firstname") private String playerFirstname;
    @JsonProperty("player_lastname") private String playerLastname;
    private String team;
    private Integer goals;
}
