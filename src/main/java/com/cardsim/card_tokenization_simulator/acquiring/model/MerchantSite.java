package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="merchantSite")
public class MerchantSite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String siteId;
    @Column(nullable = false)
    private String countryCode;
    @Column(nullable = false)
    private LocalDate openingDate;

    @OneToMany(mappedBy = "merchantSite", cascade = CascadeType.ALL)
    private List<AcceptorPoint> acceptor;
    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<AcceptorPoint> getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(List<AcceptorPoint> acceptor) {
        this.acceptor = acceptor;
    }
}
