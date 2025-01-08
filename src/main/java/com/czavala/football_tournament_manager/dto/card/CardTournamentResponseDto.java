package com.czavala.football_tournament_manager.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CardTournamentResponseDto implements Serializable {

    @JsonProperty(value = "card_type")
    private String cardType;

    @JsonProperty(value = "player_firstname")
    private String playerFirstname;

    @JsonProperty("player_lastname")
    private String playerLastname;

    @JsonProperty(value = "player_squad_number")
    private Integer playerSquadNumber;

    @JsonProperty(value = "team_name")
    private String teamName;

    @JsonProperty(value = "match_id")
    private Long matchId;

    @JsonProperty(value = "tournament_id")
    private Long tournamentId;

}
