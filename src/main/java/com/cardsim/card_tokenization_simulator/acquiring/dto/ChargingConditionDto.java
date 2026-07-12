package com.cardsim.card_tokenization_simulator.acquiring.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ChargingConditionDto {

    @NotBlank(message = "Condition name is required")
    private String conditionName;

    @NotNull(message = "Rate is required")
    private BigDecimal rate;

    @NotNull(message = "Minimum amount is required")
    private BigDecimal minAmount;

    private BigDecimal maxAmount;

    public ChargingConditionDto() {
    }

    public ChargingConditionDto(String conditionName,
                                BigDecimal rate,
                                BigDecimal minAmount,
                                BigDecimal maxAmount) {
        this.conditionName = conditionName;
        this.rate = rate;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public BigDecimal getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
}