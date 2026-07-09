package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public class CreateMerchantRequest {

    @NotBlank(message = "merchantId is required")
    private String merchantId;

    @NotBlank(message = "bankCode is required")
    @Pattern(regexp = "^[A-Z0-9]{6}$", message = "bankCode must be 6 alphanumeric characters")
    private String bankCode;

    @NotBlank(message = "companyName is required")
    private String companyName;

    private String doingBusinessAs;

    @NotBlank(message = "legalId is required")
    private String legalId;

    @NotBlank(message = "countryCode is required")
    private String countryCode;

    @NotBlank(message = "regionCode is required")
    private String regionCode;

    @NotBlank(message = "languageCode is required")
    private String languageCode;

    private Long annualTurnover;

    @Valid
    @NotNull(message = "At least one address is required")
    @Size(min = 1, message = "At least one address is required")
    private List<MerchantAddressDto> merchantAddressesDto;

    @Valid
    @NotNull(message = "At least one activity is required")
    @Size(min = 1, message = "At least one activity is required")
    private List<MerchantActivityDto> merchantActivityDto;

    @Valid
    @NotNull(message = "At least one account is required")
    @Size(min = 1, message = "At least one account is required")
    private List<MerchantAccountDto> merchantAccountDto;

    @Valid
    @NotNull(message = "At least one settlement condition is required")
    @Size(min = 1, message = "At least one settlement condition is required")
    private List<MerchantSettlementConditionDto> merchantSettlementConditionDto;

    public String getMerchantId() { return merchantId; }
    public void setMerchantId(String merchantId) { this.merchantId = merchantId; }

    public String getBankCode() { return bankCode; }
    public void setBankCode(String bankCode) { this.bankCode = bankCode; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getDoingBusinessAs() { return doingBusinessAs; }
    public void setDoingBusinessAs(String doingBusinessAs) { this.doingBusinessAs = doingBusinessAs; }

    public String getLegalId() { return legalId; }
    public void setLegalId(String legalId) { this.legalId = legalId; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public String getRegionCode() { return regionCode; }
    public void setRegionCode(String regionCode) { this.regionCode = regionCode; }

    public String getLanguageCode() { return languageCode; }
    public void setLanguageCode(String languageCode) { this.languageCode = languageCode; }

    public Long getAnnualTurnover() { return annualTurnover; }
    public void setAnnualTurnover(Long annualTurnover) { this.annualTurnover = annualTurnover; }

    public List<MerchantAddressDto> getMerchantAddressesDto() { return merchantAddressesDto; }
    public void setMerchantAddressesDto(List<MerchantAddressDto> merchantAddressesDto) { this.merchantAddressesDto = merchantAddressesDto; }

    public List<MerchantActivityDto> getMerchantActivityDto() { return merchantActivityDto; }
    public void setMerchantActivityDto(List<MerchantActivityDto> merchantActivityDto) { this.merchantActivityDto = merchantActivityDto; }

    public List<MerchantAccountDto> getMerchantAccountDto() { return merchantAccountDto; }
    public void setMerchantAccountDto(List<MerchantAccountDto> merchantAccountDto) { this.merchantAccountDto = merchantAccountDto; }

    public List<MerchantSettlementConditionDto> getMerchantSettlementConditionDto() { return merchantSettlementConditionDto; }
    public void setMerchantSettlementConditionDto(List<MerchantSettlementConditionDto> merchantSettlementConditionDto) { this.merchantSettlementConditionDto = merchantSettlementConditionDto; }
}