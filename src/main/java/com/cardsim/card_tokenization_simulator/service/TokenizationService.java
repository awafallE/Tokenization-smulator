package com.cardsim.card_tokenization_simulator.service;

import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import com.cardsim.card_tokenization_simulator.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenizationService {
    private final CardRepository cardRepository;
    private final TokenRepository tokenRepository;
    public TokenizationService(CardRepository cardRepository, TokenRepository tokenRepository) {
        this.cardRepository = cardRepository;
        this.tokenRepository = tokenRepository;
    }

}
