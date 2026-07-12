package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="charging_condition")
public class ChargingCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)

    private String conditionName;
    @Column(nullable = false)
    private BigDecimal rate;
    @Column(nullable = false)
    private BigDecimal minAmount;
    private BigDecimal maxAmount;

    @ManyToOne
    @JoinColumn(name = "billable")
    private BillableElement billableElement;

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

    public BillableElement getBillableElement() {
        return billableElement;
    }

    public void setBillableElement(BillableElement billableElement) {
        this.billableElement = billableElement;
    }
}
