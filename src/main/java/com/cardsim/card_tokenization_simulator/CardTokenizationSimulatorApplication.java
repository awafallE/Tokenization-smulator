package com.cardsim.card_tokenization_simulator;

import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.Repository;

@SpringBootApplication
public class CardTokenizationSimulatorApplication {
	//Only run once
	@Bean
public CommandLineRunner testRun(CardRepository cardRepository){


		return args -> {
			Card card = new Card();
			card.setPan("4111111111111111");
			card.setExpiryDate("12/27");
			card.setEmbossingName("AWW TEST");
			cardRepository.save(card);
			System.out.println("Saved card with id: " + card.getId());
			System.out.println(cardRepository.findAll());
		};
	}

	public static void main(String[] args) {


		SpringApplication.run(CardTokenizationSimulatorApplication.class, args);


	}

}
