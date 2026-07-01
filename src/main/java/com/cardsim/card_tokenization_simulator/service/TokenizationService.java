package com.cardsim.card_tokenization_simulator.service;

import com.cardsim.card_tokenization_simulator.dto.CardRequest;
import com.cardsim.card_tokenization_simulator.dto.TokenResponse;
import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.model.Token;
import com.cardsim.card_tokenization_simulator.model.TokenStatus;
import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import com.cardsim.card_tokenization_simulator.repository.TokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class TokenizationService {
    private final CardRepository cardRepository;
    private final TokenRepository tokenRepository;
    private  final SecureRandom secureRandom = new SecureRandom();
    //getUrlEncoder is better choice getEncoder
    private  final Base64.Encoder base64Encoder = Base64.getUrlEncoder().withoutPadding();


    //Contructor injection==> String going to create the dependency while created the Object unlike field Injection(Important for the test unit)
    public TokenizationService(CardRepository cardRepository, TokenRepository tokenRepository) {
        this.cardRepository = cardRepository;
        this.tokenRepository = tokenRepository;
    }
    @Transactional
    public TokenResponse tokenizeNewCard(CardRequest request) {
        Card  card=new Card();
        card.setPan(request.getPan());
        card.setExpiryDate(request.getExpiryDate());
        card.setEmbossingName(request.getEmbossingName());

        Card savedCard = cardRepository.save(card);
        return createToken(savedCard);
    }

    public TokenResponse tokenizeExistingCard(Long cardId) {
        Card card = cardRepository.findById(cardId)
                .orElseThrow(() -> new IllegalArgumentException("Card not found: " + cardId));
        return createToken(card);
    }

    private TokenResponse createToken (Card card) {
        String tokenValue = generateTokenValue();
        String lastFour = card.getPan().substring(card.getPan().length() - 4);

        Token token = new Token();
        token.setTokenValue(tokenValue);
        token.setLastFourDigits(lastFour);
        token.setCard(card);
        token.setStatus(TokenStatus.ACTIVE);
        token.setCreatedAt(LocalDateTime.now());
        tokenRepository.save(token);
        return new TokenResponse(tokenValue,lastFour,TokenStatus.ACTIVE);
    }

    private  String generateTokenValue() {
        byte[] randomBytes = new byte[24];
        //Using secure Random instead of Random because this last is predictible so a hacker can calculate the value
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
