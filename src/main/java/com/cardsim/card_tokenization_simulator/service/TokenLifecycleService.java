package com.cardsim.card_tokenization_simulator.service;

import com.cardsim.card_tokenization_simulator.dto.TokenResponse;
import com.cardsim.card_tokenization_simulator.model.Token;
import com.cardsim.card_tokenization_simulator.model.TokenStatus;
import com.cardsim.card_tokenization_simulator.repository.TokenRepository;
import org.springframework.stereotype.Service;

@Service
public class TokenLifecycleService {

    private final TokenRepository tokenRepository;
    public TokenLifecycleService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    public TokenResponse updateTokenStatus(String tokenValue, TokenStatus newStatus){
        Token token= tokenRepository.findByTokenValue(tokenValue).orElseThrow(() -> new IllegalArgumentException("Token not found: " + tokenValue));
        if(token.getStatus() ==TokenStatus.DELETED|| token.getStatus() ==TokenStatus.EXPIRED){
            throw new IllegalStateException ("Invalid Transition");
        } if (token.getStatus() == newStatus) {
            throw new IllegalStateException(
                    "Token is already in status: " + newStatus);
        }
        token.setStatus(newStatus);
        tokenRepository.save(token);
        return new TokenResponse( token.getTokenValue(), token.getLastFourDigits(), token.getStatus());
    }



}
