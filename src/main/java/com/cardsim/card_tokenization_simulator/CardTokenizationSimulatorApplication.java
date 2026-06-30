package com.cardsim.card_tokenization_simulator;

import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.model.Token;
import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import com.cardsim.card_tokenization_simulator.service.TokenizationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;

@SpringBootApplication
public class CardTokenizationSimulatorApplication {
	//Only run once
	@Bean
public CommandLineRunner testRun(TokenizationService tokenizationService){



		return args -> {
			Card card = new Card();
			card.setPan("4111111111111111");
			card.setExpiryDate("12/27");
			card.setEmbossingName("AWW TEST");
			Token token = tokenizationService.tokenizeNewCard(card);
			System.out.println("Created token: " + token.getTokenValue());
			System.out.println("Linked to card id: " + token.getCard().getId());
			System.out.println("Status: " + token.getStatus());

		};
	}

	public static void main(String[] args) {


		SpringApplication.run(CardTokenizationSimulatorApplication.class, args);


	}

}
