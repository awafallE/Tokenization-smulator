package com.cardsim.card_tokenization_simulator.acquiring.dto;

import com.cardsim.card_tokenization_simulator.acquiring.model.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
public class CreateContractRequest {

    @NotBlank(message = "contractReference is required")
    private String contractReference;

    private String merchantId;

    @NotBlank(message = "productCode is required")
    private String productCode;

    @NotNull(message = "contractStartDate is required")
    private LocalDate contractStartDate;

    private LocalDate contractEndDate; // optional

    @NotBlank(message = "acceptedNetworks is required")
    private String acceptedNetworks;

    @Valid
    @NotNull(message = "At least one service is required")
    @Size(min = 1, message = "At least one service is required")
    private List<ContractServiceDto> services;

    @Valid
    @NotNull(message = "At least one billable element is required")
    @Size(min = 1, message = "At least one billable element is required")
    private List<BillableElementDto> billableElements;

    @Valid
    @NotNull(message = "At least one settlement condition is required")
    @Size(min = 1, message = "At least one settlement condition is required")
    private List<ContractSettlementConditionDto> settlementConditions;

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

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getAcceptedNetworks() {
        return acceptedNetworks;
    }

    public void setAcceptedNetworks(String acceptedNetworks) {
        this.acceptedNetworks = acceptedNetworks;
    }

    public List<ContractServiceDto> getServices() {
        return services;
    }

    public void setServices(List<ContractServiceDto> services) {
        this.services = services;
    }

    public List<BillableElementDto> getBillableElements() {
        return billableElements;
    }

    public void setBillableElements(List<BillableElementDto> billableElements) {
        this.billableElements = billableElements;
    }

    public List<ContractSettlementConditionDto> getSettlementConditions() {
        return settlementConditions;
    }

    public void setSettlementConditions(List<ContractSettlementConditionDto> settlementConditions) {
        this.settlementConditions = settlementConditions;
    }
}

