package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

@Entity
@Table(name="contract_settlement_condition")
public class ContractSettlementCondition {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="contract")
    private MerchantContract merchantContract;
    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SettlementFrequency settlementFrequency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public SettlementFrequency getSettlementFrequency() {
        return settlementFrequency;
    }

    public void setSettlementFrequency(SettlementFrequency settlementFrequency) {
        this.settlementFrequency = settlementFrequency;
    }

    public MerchantContract getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(MerchantContract merchantContract) {
        this.merchantContract = merchantContract;
    }
}
