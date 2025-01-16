package com.czavala.football_tournament_manager.dto.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class CardResponseDto implements Serializable {

    @JsonProperty("card_id")
    private Long id;

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

    @JsonProperty(value = "tournament_name")
    private String tournamentName;
}
