package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;
@Entity
@Table(name ="acceptor_points" )
public class AcceptorPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String acceptorPointId;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)

    private TerminalType terminalType;
    @ManyToOne
    @JoinColumn(name = "merchantSite", nullable = false)
    private MerchantSite merchantSite;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcceptorPointId() {
        return acceptorPointId;
    }

    public void setAcceptorPointId(String acceptorPointId) {
        this.acceptorPointId = acceptorPointId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public TerminalType getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(TerminalType terminalType) {
        this.terminalType = terminalType;
    }

    public MerchantSite getMerchantSite() {
        return merchantSite;
    }

    public void setMerchantSite(MerchantSite merchantSite) {
        this.merchantSite = merchantSite;
    }
}
