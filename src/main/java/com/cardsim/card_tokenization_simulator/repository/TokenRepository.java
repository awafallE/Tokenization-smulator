package com.cardsim.card_tokenization_simulator.repository;

import com.cardsim.card_tokenization_simulator.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findbyTokenValue(String tokenValue);
}
