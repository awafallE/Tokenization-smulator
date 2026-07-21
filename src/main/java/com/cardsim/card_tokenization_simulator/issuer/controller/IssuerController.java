package com.cardsim.card_tokenization_simulator.issuer.controller;


import com.cardsim.card_tokenization_simulator.issuer.dto.ApproveProvisioningRequest;
import com.cardsim.card_tokenization_simulator.issuer.dto.ApproveProvisioningResponse;
import com.cardsim.card_tokenization_simulator.issuer.dto.EligibilityRequest;
import com.cardsim.card_tokenization_simulator.issuer.dto.EligibilityResponse;
import com.cardsim.card_tokenization_simulator.issuer.service.IssuerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issuer")
public class IssuerController {

    private final IssuerService issuerService;
    public IssuerController(IssuerService issuerService) {
        this.issuerService = issuerService;
    }


    @PostMapping("/eligibility")
    public ResponseEntity<EligibilityResponse> checkElligibilty(
            @Valid @RequestBody EligibilityRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(issuerService.checkEligibility(request));
    }


    @PostMapping("/provisioning/approve")
    public ResponseEntity<ApproveProvisioningResponse> approveProvisioning(
            @Valid @RequestBody ApproveProvisioningRequest request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(issuerService.approveProvisioning(request));
    }



}

