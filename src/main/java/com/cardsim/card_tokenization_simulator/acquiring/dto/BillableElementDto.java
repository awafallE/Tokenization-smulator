package com.cardsim.card_tokenization_simulator.acquiring.dto;


import com.cardsim.card_tokenization_simulator.acquiring.model.PayableEvent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public class BillableElementDto {

    @NotNull(message = "Payable event is required")
    private PayableEvent payableEvent;

    @Valid
    @NotNull(message = "Charging conditions are required")
    @Size(min = 1, message = "At least one charging condition is required")
    private List<ChargingConditionDto> chargingConditions;

    public BillableElementDto() {
    }

    public BillableElementDto(PayableEvent payableEvent,
                              List<ChargingConditionDto> chargingConditions) {
        this.payableEvent = payableEvent;
        this.chargingConditions = chargingConditions;
    }

    public PayableEvent getPayableEvent() {
        return payableEvent;
    }

    public void setPayableEvent(PayableEvent payableEvent) {
        this.payableEvent = payableEvent;
    }

    public List<ChargingConditionDto> getChargingConditions() {
        return chargingConditions;
    }

    public void setChargingConditions(List<ChargingConditionDto> chargingConditions) {
        this.chargingConditions = chargingConditions;
    }
}