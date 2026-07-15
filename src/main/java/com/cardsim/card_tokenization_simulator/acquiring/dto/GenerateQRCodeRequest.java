package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class GenerateQRCodeRequest {
    @NotBlank(message = "merchantId is required")
    private String merchantId;
    @NotBlank(message = "acceptorPointId is required")

    private String acceptorPointId;
    private String currency;
    private BigDecimal amount;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getAcceptorPointId() {
        return acceptorPointId;
    }

    public void setAcceptorPointId(String acceptorPointId) {
        this.acceptorPointId = acceptorPointId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
