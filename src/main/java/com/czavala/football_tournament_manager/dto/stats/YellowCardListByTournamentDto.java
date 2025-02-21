package com.czavala.football_tournament_manager.dto.stats;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class YellowCardListByTournamentDto {

    private String playerFirstname;
    private String playerLastname;
    private Integer totalYellowCards;
    private String teamName;

}
