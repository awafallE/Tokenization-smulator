package com.cardsim.card_tokenization_simulator.acquiring.controller;

import com.cardsim.card_tokenization_simulator.acquiring.dto.CreateMerchantRequest;
import com.cardsim.card_tokenization_simulator.acquiring.dto.MerchantResponse;
import com.cardsim.card_tokenization_simulator.acquiring.service.MerchantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acquiring/merchants")
public class TokenizationController {
    private final MerchantService merchantService;
    public TokenizationController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PostMapping
    public ResponseEntity<MerchantResponse> createMerchant(
            @Valid @RequestBody CreateMerchantRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(merchantService.createMerchant(request));
    }


}
