package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="merchant" )
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false ,unique = true)
    private String merchantId;
    @Column(nullable = false)
    private String bankCode;
    @Column(nullable = false)
    private String companyName;
    @Column(nullable = false)
    private String doingBusinessAs;
    @Column(nullable = false)
    private String legalId;
    @Column(nullable = false)
    private String  countryCode;
    @Column(nullable = false)
    private String regionCode;
    @Column(nullable = false)
    private String       languageCode;
    @Column(nullable = false)
    private Long annualTurnover;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private LocalDate openingDate;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)

    private List<MerchantSite> merchantSites;
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<MerchantAddress> addresses;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<MerchantActivity> activities;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<MerchantAccount> accounts;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<MerchantSettlementCondition> settlementConditions;


    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<MerchantContract> merchantContracts;

    public List<MerchantContract> getMerchantContracts() {
        return merchantContracts;
    }

    public void setMerchantContracts(List<MerchantContract> merchantContracts) {
        this.merchantContracts = merchantContracts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDoingBusinessAs() {
        return doingBusinessAs;
    }

    public void setDoingBusinessAs(String doingBusinessAs) {
        this.doingBusinessAs = doingBusinessAs;
    }

    public String getLegalId() {
        return legalId;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public Long getAnnualTurnover() {
        return annualTurnover;
    }

    public void setAnnualTurnover(Long annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public List<MerchantSite> getMerchantSites() {
        return merchantSites;
    }

    public void setMerchantSites(List<MerchantSite> merchantSites) {
        this.merchantSites = merchantSites;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<MerchantAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<MerchantAddress> addresses) {
        this.addresses = addresses;
    }

    public List<MerchantActivity> getActivities() {
        return activities;
    }

    public void setActivities(List<MerchantActivity> activities) {
        this.activities = activities;
    }

    public List<MerchantAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<MerchantAccount> accounts) {
        this.accounts = accounts;
    }

    public List<MerchantSettlementCondition> getSettlementConditions() {
        return settlementConditions;
    }

    public void setSettlementConditions(List<MerchantSettlementCondition> settlementConditions) {
        this.settlementConditions = settlementConditions;
    }
}
