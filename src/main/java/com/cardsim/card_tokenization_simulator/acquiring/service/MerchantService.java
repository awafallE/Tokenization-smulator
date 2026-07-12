package com.cardsim.card_tokenization_simulator.acquiring.service;

import com.cardsim.card_tokenization_simulator.acquiring.dto.*;
import com.cardsim.card_tokenization_simulator.acquiring.model.*;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MerchantService {

    private final MerchantRepository merchantRepository;

    // Constructor injection

    public MerchantService(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Transactional
    public MerchantResponse createMerchant(CreateMerchantRequest request) {

        // Step 1: Check merchant doesn't already exist
        // use findByMerchantId — if present throw IllegalArgumentException
        merchantRepository.findByMerchantId(request.getMerchantId()).ifPresent(merchant->  {throw new IllegalArgumentException("merchant already exist"+request.getMerchantId());});
        // Step 2: Validate business rules
       boolean addressCheck= request.getMerchantAddressesDto().stream().anyMatch(  (address)->address.getDefaultAddressFlag().equals("Y"));
        if (!addressCheck) {
            throw new IllegalArgumentException("At least one address must be the default address.");
        }
        // at least one address with defaultAddressFlag = "Y"
        // at least one activity with activityType = "P"
        boolean activityCheck= request.getMerchantActivityDto().stream().anyMatch(  (activity)->activity.getActivityType().equals("P"));
        if (!activityCheck) {
            throw new IllegalArgumentException("At least one activity must be primary");
        }
        // at least one account with accountType = "P"
        boolean accountCheck= request.getMerchantAccountDto().stream().anyMatch(  (account)->account.getAccountType().equals("P"));
        if (!accountCheck) {
            throw new IllegalArgumentException("At least one account must be primary");

        }
        // Step 3: Create Merchant entity
        Merchant newMerchant = new Merchant();
        newMerchant.setCreatedAt(LocalDateTime.now());
        newMerchant.setStatus(Status.PENDING);
        newMerchant.setBankCode(request.getBankCode());
        newMerchant.setAnnualTurnover(request.getAnnualTurnover());
        newMerchant.setCompanyName(request.getCompanyName());
        newMerchant.setCountryCode(request.getCountryCode());
        newMerchant.setLanguageCode(request.getLanguageCode());
        newMerchant.setLegalId(request.getLegalId());
        newMerchant.setMerchantId(request.getMerchantId());
        newMerchant.setDoingBusinessAs(request.getDoingBusinessAs());
        newMerchant.setRegionCode(request.getRegionCode());
        newMerchant.setOpeningDate(LocalDate.now());

        List<MerchantAddress> newAddresses = new ArrayList<>();

        // Step 4: Map addresses and link to merchant
        for (MerchantAddressDto addressDto : request.getMerchantAddressesDto()) {
            MerchantAddress newAddress = new MerchantAddress();

            newAddress.setDefaultAddressFlag(addressDto.getDefaultAddressFlag());
            newAddress.setAddress1(addressDto.getAddress1());
            newAddress.setAddress2(addressDto.getAddress2());
            newAddress.setCityName(addressDto.getCityName());
            newAddress.setCountryCode(addressDto.getCountryCode());
            newAddress.setEmail(addressDto.getEmail());
            newAddress.setCityName(addressDto.getCityName());
            newAddress.setPhone(addressDto.getPhone());
            newAddress.setRegionCode(addressDto.getRegionCode());
            newAddress.setZipCode( addressDto.getZipCode() );
            newAddress.setAddressType(addressDto.getAddressType());
            newAddress.setMerchant(newMerchant);

            newAddresses.add(newAddress);

        }
        newMerchant.setAddresses(newAddresses);

        // for each dto → create MerchantAddress → set merchant → add to list
        // merchant.setAddresses(addresses)

        // Step 5: Map activities and link to merchant
        List<MerchantActivity> newActivities= new ArrayList<>();

        for (MerchantActivityDto activity : request.getMerchantActivityDto()) {
            MerchantActivity newActivity = new MerchantActivity();

            newActivity.setActivityType(activity.getActivityType());
            newActivity.setDescription(activity.getDescription());
            newActivity.setMcc(activity.getMcc());
            newActivity.setOpeningDate(LocalDate.now());
            newActivity.setMerchant(newMerchant);
            newActivities.add(newActivity);
        }
        newMerchant.setActivities(newActivities);
        List<MerchantAccount> newAccounts = new ArrayList<>();
        for (MerchantAccountDto accountDto : request.getMerchantAccountDto()) {
            MerchantAccount newAccount = new MerchantAccount();
            newAccount.setAccountNumber(accountDto.getAccountNumber());
            newAccount.setAccountType(accountDto.getAccountType());
            newAccount.setAccountCurrency(accountDto.getAccountCurrency());
            newAccount.setIban(accountDto.getIban());
            newAccount.setBankCode(accountDto.getBankCode());
            newAccount.setBranchCode(accountDto.getBranchCode());
            newAccount.setMerchant(newMerchant);
            newAccounts.add(newAccount);
        }
        newMerchant.setAccounts(newAccounts);

        List<MerchantSettlementCondition> newConditions = new ArrayList<>();
        for (MerchantSettlementConditionDto conditionDto : request.getMerchantSettlementConditionDto()) {
            MerchantSettlementCondition newCondition = new MerchantSettlementCondition();
            newCondition.setConditionName(conditionDto.getConditionName());
            newCondition.setSettlementFrequency(conditionDto.getSettlementFrequency());
            newCondition.setAccountNumber(conditionDto.getAccountNumber());
            newCondition.setStartDate(conditionDto.getStartDate());
            newCondition.setMerchant(newMerchant);
            newConditions.add(newCondition);
        }
        newMerchant.setSettlementConditions(newConditions);

        //Default Settlement Condition
        MerchantSettlementCondition defaultSettlementCondition = new MerchantSettlementCondition();
        defaultSettlementCondition.setSettlementFrequency("DAILY");
        defaultSettlementCondition.setConditionName("Default settlement");
        defaultSettlementCondition.setStartDate(LocalDate.now());
        defaultSettlementCondition.setAccountNumber(
                request.getMerchantAccountDto().stream()
                        .filter(a -> "P".equals(a.getAccountType()))
                        .findFirst().get()
                        .getAccountNumber()
        );
        defaultSettlementCondition.setMerchant(newMerchant);
        newConditions.add(defaultSettlementCondition);

        Merchant saved=merchantRepository.save(newMerchant);
        return new MerchantResponse(
                saved.getMerchantId(),
                saved.getCompanyName(),
                saved.getBankCode(),
                saved.getStatus().name(),
                saved.getCreatedAt(),
                "Merchant created successfully - pending validation"
        );
    }
}