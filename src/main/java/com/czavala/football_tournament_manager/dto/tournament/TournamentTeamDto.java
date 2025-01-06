package com.czavala.football_tournament_manager.dto.tournament;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TournamentTeamDto {

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty(value = "team_name")
    private String teamName;

}
