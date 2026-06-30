package com.cardsim.card_tokenization_simulator.repository;

import com.cardsim.card_tokenization_simulator.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    //Spring data use dynamic proxies to generate the repository implementation at runtime based on the interface contract
    Optional<Card> findByPan(String pan);
}
