package com.cardsim.card_tokenization_simulator.controller;

import com.cardsim.card_tokenization_simulator.dto.AuthResponse;
import com.cardsim.card_tokenization_simulator.dto.LoginRequest;
import com.cardsim.card_tokenization_simulator.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
   private final AuthService authService;

   public AuthController(AuthService authService) {
       this.authService = authService;
   }
   @PostMapping("/login")
   public ResponseEntity<AuthResponse> authClient(@RequestBody @Valid LoginRequest loginRequest) {
       AuthResponse authResponse=authService.authenticate(loginRequest);
       return ResponseEntity.status(HttpStatus.OK).body(authResponse);

   }
}



