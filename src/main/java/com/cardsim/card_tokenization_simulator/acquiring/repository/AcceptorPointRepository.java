package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.AcceptorPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcceptorPointRepository extends JpaRepository<AcceptorPoint, Long> {
    Optional<AcceptorPoint> findByAcceptorPointId(String acceptorPointId);


}
