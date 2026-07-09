package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantAccountRepository extends JpaRepository<MerchantAccount,Long> {
}
