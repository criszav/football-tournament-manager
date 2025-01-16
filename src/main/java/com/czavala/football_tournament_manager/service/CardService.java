package com.czavala.football_tournament_manager.service;

import com.czavala.football_tournament_manager.dto.card.CardResponseDto;
import com.czavala.football_tournament_manager.dto.card.CreatedCardResponseDto;
import com.czavala.football_tournament_manager.dto.card.SaveCardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    Page<CardResponseDto> findAll(Pageable pageable);

    CardResponseDto findById(Long cardId);

    CreatedCardResponseDto create(SaveCardDto cardDto);

    Page<CardResponseDto> findCardsByType(String cardType, Pageable pageable);

    CardResponseDto updateById(Long cardId, SaveCardDto cardDto);
}
