package com.czavala.football_tournament_manager.dto.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeamYellowCardsDto {

    @JsonProperty("team_name") private String teamName;
    @JsonProperty("total_yellow_cards") private Integer totalYellowCards;

}
