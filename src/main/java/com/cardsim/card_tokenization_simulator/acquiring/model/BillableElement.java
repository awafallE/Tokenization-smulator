package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="billable")
public class BillableElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)

    @Enumerated(EnumType.STRING)
    private PayableEvent payableEvent;
    @ManyToOne
    @JoinColumn(name = "mer_contract", nullable = false)
    private MerchantContract merchantContract;
    @OneToMany(mappedBy = "billableElement", cascade = CascadeType.ALL)
    private List<ChargingCondition> chargingCondition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayableEvent getPayableEvent() {
        return payableEvent;
    }

    public void setPayableEvent(PayableEvent payableEvent) {
        this.payableEvent = payableEvent;
    }

    public MerchantContract getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(MerchantContract merchantContract) {
        this.merchantContract = merchantContract;
    }

    public List<ChargingCondition> getChargingCondition() {
        return chargingCondition;
    }

    public void setChargingCondition(List<ChargingCondition> chargingCondition) {
        this.chargingCondition = chargingCondition;
    }
}
