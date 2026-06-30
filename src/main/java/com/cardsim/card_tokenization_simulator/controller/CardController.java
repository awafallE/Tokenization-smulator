package com.cardsim.card_tokenization_simulator.controller;

import com.cardsim.card_tokenization_simulator.service.TokenizationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cards")
public class CardController {

     private final TokenizationService tokenizationService;
     public CardController(TokenizationService tokenizationService) {
        this.tokenizationService = tokenizationService;
    }
}
