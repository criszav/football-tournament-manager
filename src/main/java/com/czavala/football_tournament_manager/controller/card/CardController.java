package com.czavala.football_tournament_manager.controller.card;

import com.czavala.football_tournament_manager.dto.card.CardResponseDto;
import com.czavala.football_tournament_manager.dto.card.CreatedCardResponseDto;
import com.czavala.football_tournament_manager.dto.card.SaveCardDto;
import com.czavala.football_tournament_manager.service.CardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<Page<CardResponseDto>> findAllCards(Pageable pageable) {
        Page<CardResponseDto> cards = cardService.findAll(pageable);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> findOneById(@PathVariable Long cardId) {
        CardResponseDto card = cardService.findById(cardId);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/type/{cardType}")
    public ResponseEntity<Page<CardResponseDto>> findCardsByType(@PathVariable String cardType, Pageable pageable) {
        Page<CardResponseDto> cardsByType = cardService.findCardsByType(cardType, pageable);
        return ResponseEntity.ok(cardsByType);
    }
    @PostMapping
    public ResponseEntity<CreatedCardResponseDto> createOne(@RequestBody @Valid SaveCardDto cardDto,
                                                            HttpServletRequest request) {
        CreatedCardResponseDto newCard = cardService.create(cardDto);

        String baseUrl = request.getRequestURL().toString();
        URI newCardLocation = URI.create(baseUrl + "/" + newCard.getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(newCardLocation)
                .body(newCard);
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<CardResponseDto> updateOneById(@PathVariable Long cardId,
                                                         @RequestBody @Valid SaveCardDto cardDto) {
        CardResponseDto updatedCard = cardService.updateById(cardId, cardDto);
        return ResponseEntity.ok(updatedCard);
    }

}
