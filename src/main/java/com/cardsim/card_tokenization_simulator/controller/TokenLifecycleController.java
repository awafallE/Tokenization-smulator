package com.cardsim.card_tokenization_simulator.controller;

import com.cardsim.card_tokenization_simulator.dto.TokenResponse;
import com.cardsim.card_tokenization_simulator.model.TokenStatus;
import com.cardsim.card_tokenization_simulator.service.TokenLifecycleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
public class TokenLifecycleController {
   private final TokenLifecycleService tokenLifecycleService;

    public TokenLifecycleController(TokenLifecycleService tokenLifecycleService) {
        this.tokenLifecycleService = tokenLifecycleService;
    }

    @PatchMapping("/tokens/{tokenValue}/suspend")
    public ResponseEntity<TokenResponse> suspendToken(@PathVariable String tokenValue) {
        TokenResponse token =tokenLifecycleService.updateTokenStatus(tokenValue,TokenStatus.SUSPENDED)   ;
        return  ResponseEntity.status(HttpStatus.OK).body(token);
    }
    @PatchMapping("/tokens/{tokenValue}/revoke")
    public ResponseEntity<TokenResponse> revokeToken(@PathVariable String tokenValue) {
        TokenResponse token =tokenLifecycleService.updateTokenStatus(tokenValue,TokenStatus.DELETED)   ;
        return  ResponseEntity.status(HttpStatus.OK).body(token);
    }

    @PatchMapping("/tokens/{tokenValue}/reactivate")
    public ResponseEntity<TokenResponse> reactiveToken(@PathVariable String tokenValue) {
        TokenResponse token =tokenLifecycleService.updateTokenStatus(tokenValue,TokenStatus.ACTIVE)   ;
        return  ResponseEntity.status(HttpStatus.OK).body(token);
    }

}
