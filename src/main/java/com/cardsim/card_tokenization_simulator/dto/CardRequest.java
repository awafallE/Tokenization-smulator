package com.cardsim.card_tokenization_simulator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class CardRequest {

    @NotBlank(message = "PAN is required")
    @Pattern(regexp = "^[0-9]{13,19}$", message = "PAN must be 13-19 digits")
    private String pan;

   /* @NotBlank(message = "Expiry date is required")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/([0-9]{2})$", message = "Expiry date must be in MM/YY format")*/
    private LocalDate expiryDate;

    @NotBlank(message = "Embossing name is required")
    private String embossingName;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getEmbossingName() {
        return embossingName;
    }

    public void setEmbossingName(String embossingName) {
        this.embossingName = embossingName;
    }
}