package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantSite;
import com.cardsim.card_tokenization_simulator.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantSiteRepository extends JpaRepository<MerchantSite, Long> {
}
