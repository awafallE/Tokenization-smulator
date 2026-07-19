package com.cardsim.card_tokenization_simulator.issuer.service;

import com.cardsim.card_tokenization_simulator.issuer.dto.ApproveProvisioningRequest;
import com.cardsim.card_tokenization_simulator.issuer.dto.ApproveProvisioningResponse;
import com.cardsim.card_tokenization_simulator.issuer.dto.EligibilityRequest;
import com.cardsim.card_tokenization_simulator.issuer.dto.EligibilityResponse;
import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class IssuerService {

    private final CardRepository cardRepository;

    public IssuerService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public EligibilityResponse checkEligibility(EligibilityRequest request) {

        EligibilityResponse response = new EligibilityResponse();

        // Step 1: detect card brand from PAN
        String pan = request.getPan();
        String cardBrand;

        if (pan.startsWith("4")) {
            cardBrand = "VISA";
        } else if (pan.startsWith("5")) {
            cardBrand = "MASTERCARD";
        } else {
            response.setEligible(false);
            response.setReason("PAN brand not supported");
            return response;
        }

        // Step 2: cross validate scheme vs cardBrand
        if (!request.getScheme().equalsIgnoreCase(cardBrand)) {
            response.setEligible(false);
            response.setReason("Scheme does not match card brand");
            return response;
        }

        // Step 3: check card exists in database
        Optional<Card> card = cardRepository.findByPan(pan);
        if (card.isEmpty()) {
            response.setEligible(false);
            response.setReason("Card not found");
            return response;
        }

        // Step 4: all checks passed
        response.setEligible(true);
        response.setCardBrand(cardBrand);
        response.setScheme(request.getScheme());
        response.setReason("Card is eligible for tokenization");
        return response;
    }


    public ApproveProvisioningResponse approveProvisioning(
            ApproveProvisioningRequest request) {

        ApproveProvisioningResponse response =
                new ApproveProvisioningResponse();

        // Step 1: find card
        Optional<Card> card = cardRepository.findByPan(request.getPan());
        if (card.isEmpty()) {
            response.setDecision("DECLINE");
            response.setActionCode("14");
            response.setReason("Card not found");
            return response;
        }

        Card foundCard = card.get();

        // Step 2: check expiry
        if (foundCard.getExpiryDate().isBefore(LocalDate.now())) {
            response.setDecision("DECLINE");
            response.setActionCode("54");
            response.setReason("Card expired");
            return response;
        }

        // Step 3: risk decision based on walletType
        if ("APPLE_PAY".equals(request.getWalletType())) {
            response.setDecision("APPROVE_WITH_STEP_UP");
            response.setActionCode("00");
        } else {
            // GOOGLE_PAY, SAMSUNG_PAY, null → approve directly
            response.setDecision("APPROVE");
            response.setActionCode("00");
        }

        return response;
    }
    }
