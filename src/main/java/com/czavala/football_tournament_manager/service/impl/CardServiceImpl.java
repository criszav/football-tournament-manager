package com.czavala.football_tournament_manager.service.impl;

import com.czavala.football_tournament_manager.dto.card.CardResponseDto;
import com.czavala.football_tournament_manager.dto.card.CreatedCardResponseDto;
import com.czavala.football_tournament_manager.dto.card.SaveCardDto;
import com.czavala.football_tournament_manager.exception.ResourceNotFoundException;
import com.czavala.football_tournament_manager.mapper.card.CardMapper;
import com.czavala.football_tournament_manager.persistance.entity.Card;
import com.czavala.football_tournament_manager.persistance.repository.CardRepository;
import com.czavala.football_tournament_manager.service.CardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CardResponseDto> findAll(Pageable pageable) {
        return cardRepository.findAll(pageable)
                .map(CardMapper::mapToCardResponseDto);
    }

    @Transactional(readOnly = true)
    @Override
    public CardResponseDto findById(Long cardId) {
        return CardMapper.mapToCardResponseDto(this.findCardEntityById(cardId));
    }

    @Transactional(readOnly = true)
    private Card findCardEntityById(Long cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card con ID " + cardId + " no encontrado"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<CardResponseDto> findCardsByType(String cardType, Pageable pageable) {
        return cardRepository.findByCardType(cardType, pageable)
                .map(CardMapper::mapToCardResponseDto);
    }

    @Override
    public CreatedCardResponseDto create(SaveCardDto cardDto) {
        Card card = CardMapper.mapToCardEntity(cardDto);
        cardRepository.save(card);

        return CardMapper.mapToCreatedCardDto(card);
    }

    @Override
    public CardResponseDto updateById(Long cardId, SaveCardDto cardDto) {
        Card cardFromDB = this.findCardEntityById(cardId);
        cardFromDB.setCardType(cardDto.getCardType());
        cardFromDB.setPlayerId(cardDto.getPlayerId());
        cardFromDB.setMatchId(cardDto.getMatchId());
        cardFromDB.setTeamId(cardDto.getTeamId());
        cardFromDB.setTournamentId(cardDto.getTournamentId());
        cardRepository.save(cardFromDB);

        return CardMapper.mapToCardResponseDto(cardFromDB);
    }
}
