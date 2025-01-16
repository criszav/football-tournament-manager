package com.czavala.football_tournament_manager.mapper.card;

import com.czavala.football_tournament_manager.dto.card.CardResponseDto;
import com.czavala.football_tournament_manager.dto.card.CardTournamentResponseDto;
import com.czavala.football_tournament_manager.dto.card.CreatedCardResponseDto;
import com.czavala.football_tournament_manager.dto.card.SaveCardDto;
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

    public static CreatedCardResponseDto mapToCreatedCardDto(Card cardEntity) {

        if (cardEntity == null) return null;

        return CreatedCardResponseDto.builder()
                .id(cardEntity.getId())
                .cardType(cardEntity.getCardType().name())
                .playerId(cardEntity.getPlayerId())
                .teamId(cardEntity.getTeamId())
                .matchId(cardEntity.getMatchId())
                .tournamentId(cardEntity.getTournamentId())
                .build();
    }

    public static Card mapToCardEntity(SaveCardDto cardDto) {

        if (cardDto == null) return null;

        Card card = new Card();
        card.setCardType(cardDto.getCardType());
        card.setPlayerId(cardDto.getPlayerId());
        card.setTeamId(cardDto.getTeamId());
        card.setMatchId(cardDto.getMatchId());
        card.setTournamentId(cardDto.getTournamentId());

        return card;
    }

    public static CardResponseDto mapToCardResponseDto(Card cardEntity) {

        if (cardEntity == null) return null;

        return CardResponseDto.builder()
                .id(cardEntity.getId())
                .cardType(cardEntity.getCardType().name())
                .playerFirstname(cardEntity.getPlayer().getFirstname())
                .playerLastname(cardEntity.getPlayer().getLastname())
                .playerSquadNumber(cardEntity.getPlayer().getSquadNumber())
                .teamName(cardEntity.getTeam().getName())
                .tournamentName(cardEntity.getTournament().getName())
                .build();
    }

}
