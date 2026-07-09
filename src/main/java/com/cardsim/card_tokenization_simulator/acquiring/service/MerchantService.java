package com.cardsim.card_tokenization_simulator.acquiring.service;

import com.cardsim.card_tokenization_simulator.acquiring.dto.CreateMerchantRequest;
import com.cardsim.card_tokenization_simulator.acquiring.dto.MerchantAddressDto;
import com.cardsim.card_tokenization_simulator.acquiring.dto.MerchantResponse;
import com.cardsim.card_tokenization_simulator.acquiring.model.Merchant;
import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantActivity;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
            throw new IllegalArgumentException("At least one activity must be primary");
        }
        // Step 3: Create Merchant entity
        // set all fields from request
        // set status = Status.PENDING
        // set createdAt = LocalDateTime.now()


        // Step 4: Map addresses and link to merchant
        // for each dto → create MerchantAddress → set merchant → add to list
        // merchant.setAddresses(addresses)

        // Step 5: Map activities and link to merchant

        // Step 6: Map accounts and link to merchant

        // Step 7: Map settlement conditions and link to merchant

        // Step 8: Save merchant (cascade saves everything)
        // merchantRepository.save(merchant)

        // Step 9: Return MerchantResponse
    }
}