package com.czavala.football_tournament_manager.dto.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamGoalsScoredDto {

    @JsonProperty("team_name") private String teamName;
    private Integer goals;
}
