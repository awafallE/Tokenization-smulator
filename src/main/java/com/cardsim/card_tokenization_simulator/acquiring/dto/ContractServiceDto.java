package com.cardsim.card_tokenization_simulator.acquiring.dto;

import com.cardsim.card_tokenization_simulator.acquiring.model.ServiceName;
import jakarta.validation.constraints.NotNull;

public class ContractServiceDto {
    @NotNull(message = "Service name is required")

    private ServiceName serviceName;

    @NotNull(message = "Enabled flag is required")
    private Boolean enabled;

    public ContractServiceDto() {
    }

    public ContractServiceDto(ServiceName serviceName, Boolean enabled) {
        this.serviceName = serviceName;
        this.enabled = enabled;
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
}
