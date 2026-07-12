package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract_service")
public class ContractService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ServiceName serviceName;
    @Column(nullable = false)
    private Boolean enabled;
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private MerchantContract merchantContract;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public void setServiceName(ServiceName serviceName) {
        this.serviceName = serviceName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public MerchantContract getMerchantContract() {
        return merchantContract;
    }

    public void setMerchantContract(MerchantContract merchantContract) {
        this.merchantContract = merchantContract;
    }
}
