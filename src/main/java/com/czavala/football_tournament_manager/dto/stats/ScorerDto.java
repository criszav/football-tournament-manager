package com.czavala.football_tournament_manager.dto.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScorerDto {

    @JsonProperty("player_firstname") private String playerFirstname;
    @JsonProperty("player_lastname") private String playerLastname;
    private String team;
    private Integer goals;
}
