package com.czavala.football_tournament_manager.mapper.card;

import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.persistance.entity.Card;

public class CardMapper {

    public static CardTournamentResponseDto mapToCardTournamentDto(Card cardEntity) {

        if (cardEntity == null) return null;

        return CardTournamentResponseDto.builder()
                .cardType(cardEntity.getCardType().name())
                .playerFirstname(cardEntity.getPlayer().getFirstname())
                .playerLastname(cardEntity.getPlayer().getLastname())
                .playerSquadNumber(cardEntity.getPlayer().getSquadNumber())
                .teamName(cardEntity.getTeam().getName())
                .matchId(cardEntity.getMatchId())
                .tournamentId(cardEntity.getTournamentId())
                .build();

    }
}
