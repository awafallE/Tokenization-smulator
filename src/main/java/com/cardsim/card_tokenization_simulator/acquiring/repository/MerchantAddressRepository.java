package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantAddressRepository extends JpaRepository<MerchantAddress,Long> {
}
