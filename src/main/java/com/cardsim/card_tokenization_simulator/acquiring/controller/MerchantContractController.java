package com.cardsim.card_tokenization_simulator.controller;

import com.cardsim.card_tokenization_simulator.acquiring.dto.CreateContractRequest;
import com.cardsim.card_tokenization_simulator.acquiring.dto.MerchantContractResponse;
import com.cardsim.card_tokenization_simulator.acquiring.service.MerchantContractService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/acquiring/contracts")
public class MerchantContractController {

    private final MerchantContractService merchantContractService;
    public MerchantContractController(MerchantContractService merchantContractService) {
        this.merchantContractService = merchantContractService;
    }

    @PostMapping
    public ResponseEntity<MerchantContractResponse> createContract(
            @RequestParam String merchantId,
            @Valid @RequestBody CreateContractRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(merchantContractService.createContract(merchantId, request));
    }

    @PatchMapping("/{contractReference}/activate")
    public ResponseEntity<MerchantContractResponse> activate(
            @PathVariable String contractReference) {
        return ResponseEntity.ok(merchantContractService.activateContract(contractReference));
    }

    // write suspend, unsuspend, close yourself — same pattern
}