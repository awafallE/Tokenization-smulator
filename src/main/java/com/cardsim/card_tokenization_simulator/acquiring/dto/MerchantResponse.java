package com.cardsim.card_tokenization_simulator.acquiring.dto;


import java.time.LocalDateTime;

public class MerchantResponse {

    private String merchantId;
    private String companyName;
    private String bankCode;
    private String status;
    private LocalDateTime createdAt;
    private String message;

    public MerchantResponse(String merchantId,
                            String companyName,
                            String bankCode,
                            String status,
                            LocalDateTime createdAt,
                            String message) {
        this.merchantId = merchantId;
        this.companyName = companyName;
        this.bankCode = bankCode;
        this.status = status;
        this.createdAt = createdAt;
        this.message = message;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getMessage() {
        return message;
    }
}