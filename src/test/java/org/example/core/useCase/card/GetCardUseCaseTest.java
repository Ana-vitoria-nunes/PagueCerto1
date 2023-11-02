package org.example.core.useCase.card;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.port.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetCardUseCaseTest {
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private GetCardUseCase getCardUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getCardUseCase = new GetCardUseCase(cardRepository);
    }

    @Test
    public void testFindAll() {
        CardRequest cardRequest=new CardRequest("ahyfdvfd5bv436d9","Ana Silva","7994561237895","548","15/09/2034", BigDecimal.valueOf(1500));
        Address address=new Address();
        address.setId_Address(1L);

        Costumer costumer=new Costumer(1L,"dgfnhgfhnfh284hn","Ana Silva","ana@gmail.com","ana123", LocalDate.of(2004,05,11),"1578596","34997856241",address);

        List<Debts> debtsList = new ArrayList<>();

        List<Card> cardList = new ArrayList<>();
        Card card=new Card();
        card.setCardLimit(cardRequest.getLimiteCartao());
        card.setExpiryDate(cardRequest.getDataDeValidade());
        card.setNumberCard(cardRequest.getNumeroCartao());
        card.setCvv(cardRequest.getCvv());
        card.setNameCostumerCard(cardRequest.getNomeClienteCartao());
        card.setCostumer(costumer);
        card.setDebts(debtsList);
        cardList.add(card);

        when(cardRepository.findAll()).thenReturn(cardList);
        List<CardRequest> cardRequests = getCardUseCase.findAll();
        assertEquals(1, cardRequests.size());

    }

}