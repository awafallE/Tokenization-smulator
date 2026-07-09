package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MerchantAccountDto {

    @Pattern(regexp = "^[PSH]$", message = "accountType must be P, S or H")
    private String accountType;
    @NotBlank(message = "accountCurrency is required")
    private String accountCurrency;
    private String  iban;
    @NotBlank(message = "branchCode is required")
    private String  branchCode;
    @NotBlank(message = "accountNumber is required")
    private String accountNumber;

    @NotBlank(message = "bankCode is required")
    private String bankCode;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(String accountCurrency) {
        this.accountCurrency = accountCurrency;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }
}
