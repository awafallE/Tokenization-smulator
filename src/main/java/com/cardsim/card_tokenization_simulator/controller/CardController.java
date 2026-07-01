package com.cardsim.card_tokenization_simulator.controller;

import com.cardsim.card_tokenization_simulator.dto.CardRequest;
import com.cardsim.card_tokenization_simulator.dto.TokenResponse;
import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.service.TokenizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

     private final TokenizationService tokenizationService;
     public CardController(TokenizationService tokenizationService) {
        this.tokenizationService = tokenizationService;
    }

    @PostMapping
    public ResponseEntity<TokenResponse> tokenizeNewCard(@Valid  @RequestBody CardRequest card) {
        TokenResponse token = tokenizationService.tokenizeNewCard(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }


}
