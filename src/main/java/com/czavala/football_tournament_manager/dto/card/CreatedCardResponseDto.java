package com.czavala.football_tournament_manager.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreatedCardResponseDto {

    @JsonProperty("card_id")
    private Long id;

    @JsonProperty("card_type")
    private String cardType;

    @JsonProperty("player_id")
    private Long playerId;

    @JsonProperty("match_id")
    private Long matchId;

    @JsonProperty("team_id")
    private Long teamId;

    @JsonProperty("tournament_id")
    private Long tournamentId;
}
