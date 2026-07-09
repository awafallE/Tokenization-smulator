package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class MerchantActivityDto {
    @Pattern(regexp = "^[0-9]{4}$")
    @NotBlank(message = "mcc is required")
    private String mcc;
    @Pattern(regexp = "^[PS]$")
    @NotBlank(message = "activityType is required")
    private String  activityType;
    @NotNull
    private LocalDate openingDate;
    private String  description;

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
