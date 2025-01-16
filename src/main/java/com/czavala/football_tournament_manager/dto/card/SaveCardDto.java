package com.czavala.football_tournament_manager.dto.card;

import com.czavala.football_tournament_manager.utils.CardType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class SaveCardDto implements Serializable {

    @JsonProperty("card_type")
    @NotNull(message = "{generic.notnull}")
    private CardType cardType;

    @JsonProperty("player_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long playerId;

    @JsonProperty("match_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long matchId;

    @JsonProperty("team_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long teamId;

    @JsonProperty("tournament_id")
    @NotNull(message = "{generic.notnull}")
    @Positive(message = "{generic.positive}")
    private Long tournamentId;

}
