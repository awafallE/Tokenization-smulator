package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantActivityRepository extends JpaRepository<MerchantActivity,Long> {
}
