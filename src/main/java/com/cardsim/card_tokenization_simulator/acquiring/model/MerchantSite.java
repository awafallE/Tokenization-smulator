package com.cardsim.card_tokenization_simulator.acquiring.model;

import jakarta.persistence.ManyToOne;


public class MerchantSite {
    @ManyToOne
    private Merchant merchant;

}
