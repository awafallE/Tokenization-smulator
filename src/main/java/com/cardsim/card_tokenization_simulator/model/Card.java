package com.cardsim.card_tokenization_simulator.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String pan;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(nullable = false)
    private String embossingName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        if (pan != null && pan.length() > 3) {
            return "Card{id=" + id + ", pan=**************" + pan.substring(pan.length()-4)  + ", expiryDate='" + expiryDate + "', embossingName='" + embossingName + "'}";
        }

        else  return "Card{id=" + id + ", pan=" + "N/A " + ", expiryDate='" + expiryDate + "', embossingName='" + embossingName + "'}";

    }
}
