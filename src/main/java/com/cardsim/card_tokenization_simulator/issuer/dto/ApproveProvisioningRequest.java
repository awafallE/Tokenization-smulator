package com.cardsim.card_tokenization_simulator.issuer.dto;

import jakarta.validation.constraints.NotBlank;

public class ApproveProvisioningRequest {
    @NotBlank(message = "pan is required")
    private String pan;

    @NotBlank(message = "scheme is required")
    private String scheme; // VISA, MASTERCARD

    @NotBlank(message = "panReferenceId is required")
    private String panReferenceId;
    private String tokenReferenceId; //null for in-app flow
    private String tokenRequestorId;
    private String deviceId;
    private String walletType;

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getPanReferenceId() {
        return panReferenceId;
    }

    public void setPanReferenceId(String panReferenceId) {
        this.panReferenceId = panReferenceId;
    }

    public String getTokenReferenceId() {
        return tokenReferenceId;
    }

    public void setTokenReferenceId(String tokenReferenceId) {
        this.tokenReferenceId = tokenReferenceId;
    }

    public String getTokenRequestorId() {
        return tokenRequestorId;
    }

    public void setTokenRequestorId(String tokenRequestorId) {
        this.tokenRequestorId = tokenRequestorId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getWalletType() {
        return walletType;
    }

    public void setWalletType(String walletType) {
        this.walletType = walletType;
    }
}
