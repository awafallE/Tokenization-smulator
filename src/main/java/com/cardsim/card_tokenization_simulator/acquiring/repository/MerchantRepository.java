package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    //Spring data use dynamic proxies to generate the repository implementation at runtime based on the interface contract
    Optional<Merchant> findByMerchantId(String merchantId);

}
