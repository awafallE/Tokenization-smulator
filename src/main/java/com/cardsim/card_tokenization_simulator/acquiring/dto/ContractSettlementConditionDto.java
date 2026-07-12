package com.cardsim.card_tokenization_simulator.acquiring.dto;


import com.cardsim.card_tokenization_simulator.acquiring.model.SettlementFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ContractSettlementConditionDto {

    @NotNull(message = "Settlement frequency is required")
    private SettlementFrequency settlementFrequency;

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    public ContractSettlementConditionDto() {
    }

    public ContractSettlementConditionDto(SettlementFrequency settlementFrequency,
                                          String accountNumber) {
        this.settlementFrequency = settlementFrequency;
        this.accountNumber = accountNumber;
    }

    public SettlementFrequency getSettlementFrequency() {
        return settlementFrequency;
    }

    public void setSettlementFrequency(SettlementFrequency settlementFrequency) {
        this.settlementFrequency = settlementFrequency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}