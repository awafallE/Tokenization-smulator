package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class MerchantSettlementConditionDto {
    @NotBlank(message = "conditionName is required")
    private String conditionName;
    @NotBlank(message = "settlementFrequency is required")
    @Pattern(regexp = "^(DAILY|WEEKLY|MONTHLY)$")
    private String settlementFrequency;
    @NotBlank(message = "accountNumber is required")
    private String accountNumber;
    @NotNull
    private LocalDate startDate;

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getSettlementFrequency() {
        return settlementFrequency;
    }

    public void setSettlementFrequency(String settlementFrequency) {
        this.settlementFrequency = settlementFrequency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
