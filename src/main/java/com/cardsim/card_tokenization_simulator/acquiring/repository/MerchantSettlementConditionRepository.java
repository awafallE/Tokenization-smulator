package com.cardsim.card_tokenization_simulator.acquiring.repository;

import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantSettlementCondition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantSettlementConditionRepository extends JpaRepository<MerchantSettlementCondition,Long> {
}
