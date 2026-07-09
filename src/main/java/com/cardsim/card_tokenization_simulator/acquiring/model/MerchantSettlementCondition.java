package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="settlement_condition")
public class MerchantSettlementCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String conditionName;
    @Column(nullable = false)
    private String settlementFrequency; // "DAILY", "WEEKLY", "MONTHLY"
    @Column(nullable = false)
    private String accountNumber;       // which account receives settlement
    @Column(nullable = false)
    private LocalDate startDate;
    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
