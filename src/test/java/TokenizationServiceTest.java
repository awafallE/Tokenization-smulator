import com.cardsim.card_tokenization_simulator.dto.CardRequest;
import com.cardsim.card_tokenization_simulator.dto.TokenResponse;
import com.cardsim.card_tokenization_simulator.model.Card;
import com.cardsim.card_tokenization_simulator.model.Token;
import com.cardsim.card_tokenization_simulator.model.TokenStatus;
import com.cardsim.card_tokenization_simulator.repository.CardRepository;
import com.cardsim.card_tokenization_simulator.repository.TokenRepository;
import com.cardsim.card_tokenization_simulator.service.TokenizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TokenizationServiceTest {

    @Mock
    private CardRepository cardRepository;

    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenizationService tokenizationService;
    @Test
    void tokenizeNewCard_shouldCreateTokenForNewCard() {
        // ARRANGE
        CardRequest request = new CardRequest();
        request.setPan("4111111111111111");
        request.setExpiryDate("12/27");
        request.setEmbossingName("AWW TEST");

        Card savedCard = new Card();
        savedCard.setId(1L);
        savedCard.setPan("4111111111111111");
        savedCard.setExpiryDate("12/27");
        savedCard.setEmbossingName("AWW TEST");

        when(cardRepository.findByPan("4111111111111111"))
                .thenReturn(Optional.empty());
        when(cardRepository.save(any(Card.class)))
                .thenReturn(savedCard);
        when(tokenRepository.save(any(Token.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        TokenResponse response = tokenizationService.tokenizeNewCard(request);

        // ASSERT
        assertNotNull(response.getTokenValue());
        assertEquals("1111", response.getLastFourDigits());
        assertEquals(TokenStatus.ACTIVE, response.getStatus());
        verify(cardRepository).save(any(Card.class));
        verify(tokenRepository).save(any(Token.class));
    }


}