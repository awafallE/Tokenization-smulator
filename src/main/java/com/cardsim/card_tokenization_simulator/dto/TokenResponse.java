package com.cardsim.card_tokenization_simulator.dto;

import com.cardsim.card_tokenization_simulator.model.TokenStatus;

public class TokenResponse {
    private String tokenValue;
    private String lastFourDigits;
    private TokenStatus status;

    public TokenResponse(String tokenValue, String lastFourDigits, TokenStatus status) {
        this.tokenValue = tokenValue;
        this.lastFourDigits = lastFourDigits;
        this.status = status;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public String getLastFourDigits() {
        return lastFourDigits;
    }

    public TokenStatus getStatus() {
        return status;
    }
}
