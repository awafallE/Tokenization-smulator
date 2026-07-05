package com.cardsim.card_tokenization_simulator.service;

import com.cardsim.card_tokenization_simulator.dto.AuthResponse;
import com.cardsim.card_tokenization_simulator.dto.LoginRequest;
import com.cardsim.card_tokenization_simulator.exception.InvalidCredentialsException;
import com.cardsim.card_tokenization_simulator.security.JwtService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final Map<String, String[]> users = Map.of(
            "admin", new String[]{"admin123", "ROLE_ADMIN", "BANK_001"},
            "user1", new String[]{"user123", "ROLE_USER", "BANK_001"},
            "user2", new String[]{"user456", "ROLE_USER", "BANK_002"}
    );


    public AuthService(JwtService jwtService) {
        this.jwtService = jwtService;

    }

    public AuthResponse authenticate(LoginRequest loginRequest) {
        String [] userDetails = users.get(loginRequest.getUsername());
        if (userDetails == null || !userDetails[0].equals(loginRequest.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        return new AuthResponse(jwtService.generateToken(loginRequest.getUsername(), userDetails[1], userDetails[2]));
    }
}
