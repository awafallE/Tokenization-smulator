package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantContractRepository
        extends JpaRepository<MerchantContract, Long> {
    Optional<MerchantContract> findByContractReference(String contractReference);
}