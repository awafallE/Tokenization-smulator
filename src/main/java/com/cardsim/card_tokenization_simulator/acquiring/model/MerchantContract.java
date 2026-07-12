package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "contract")
public class MerchantContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String contractReference;
    @Column(nullable = false)
    private String productCode;
    @Column(nullable = false)
    private LocalDate contractStartDate;
    private LocalDate contractEndDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(nullable = false)
    private String acceptedNetworks;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private Merchant merchant;
    @OneToMany(mappedBy = "merchantContract", cascade = CascadeType.ALL)
    List<ContractService> contractServices;
    @OneToMany(mappedBy = "merchantContract", cascade = CascadeType.ALL)
    List<BillableElement> billableElement;
    @OneToMany(mappedBy = "merchantContract", cascade = CascadeType.ALL)
    List<ContractSettlementCondition> contractSettlementCondition;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractReference() {
        return contractReference;
    }

    public void setContractReference(String contractReference) {
        this.contractReference = contractReference;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public LocalDate getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(LocalDate contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAcceptedNetworks() {
        return acceptedNetworks;
    }

    public void setAcceptedNetworks(String acceptedNetworks) {
        this.acceptedNetworks = acceptedNetworks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<ContractService> getContractServices() {
        return contractServices;
    }

    public void setContractServices(List<ContractService> contractServices) {
        this.contractServices = contractServices;
    }

    public List<BillableElement> getBillableElement() {
        return billableElement;
    }

    public void setBillableElement(List<BillableElement> billableElement) {
        this.billableElement = billableElement;
    }

    public List<ContractSettlementCondition> getContractSettlementCondition() {
        return contractSettlementCondition;
    }

    public void setContractSettlementCondition(List<ContractSettlementCondition> contractSettlementCondition) {
        this.contractSettlementCondition = contractSettlementCondition;
    }
}
