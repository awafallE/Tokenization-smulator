package com.cardsim.card_tokenization_simulator.acquiring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class MerchantAddressDto {
    @Pattern(regexp = "^[YN]$")
    @NotBlank(message = "defaultAddressFlag is required")
    private String defaultAddressFlag;
    @NotBlank(message = "address1 is required")
    private String address1;
    @NotBlank(message = "cityName is required")
    private String cityName;
    @NotBlank(message = "countryCode is required")
    private String countryCode;
    private String address2;
    @NotBlank(message = "zipCode is required")
    private String  zipCode;
    @Email(message = "email must be valid")
    @NotBlank(message = "email is required")
    private String email;
    @NotBlank(message = "phone is required")
    private String    phone;
    @NotBlank(message = "addressType is required")
    private String     addressType;
    @NotBlank(message = "regionCode is required")
    private String regionCode;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getDefaultAddressFlag() {
        return defaultAddressFlag;
    }

    public void setDefaultAddressFlag(String defaultAddressFlag) {
        this.defaultAddressFlag = defaultAddressFlag;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
