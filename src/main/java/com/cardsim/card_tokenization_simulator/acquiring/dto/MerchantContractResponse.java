package com.cardsim.card_tokenization_simulator.acquiring.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MerchantContractResponse {

    private String contractReference;
    private String merchantId;
    private String productCode;
    private String status;
    private LocalDate contractStartDate;
    private String acceptedNetworks;
    private LocalDateTime createdAt;
    private String message;

    public MerchantContractResponse() {
    }

    public MerchantContractResponse(String contractReference,
                                    String merchantId,
                                    String productCode,
                                    String status,
                                    LocalDate contractStartDate,
                                    String acceptedNetworks,
                                    LocalDateTime createdAt,
                                    String message) {
        this.contractReference = contractReference;
        this.merchantId = merchantId;
        this.productCode = productCode;
        this.status = status;
        this.contractStartDate = contractStartDate;
        this.acceptedNetworks = acceptedNetworks;
        this.createdAt = createdAt;
        this.message = message;
    }

    public String getContractReference() {
        return contractReference;
    }

    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public String getAcceptedNetworks() {
        return acceptedNetworks;
    }

    public void setAcceptedNetworks(String acceptedNetworks) {
        this.acceptedNetworks = acceptedNetworks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}