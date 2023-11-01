package org.example.core.useCase.debts;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.domain.model.dto.requestDto.DebtsRequest;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.example.core.useCase.card.GetCardUseCase;
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

class GetDebtsUseCaseTest {
    @Mock
    private DebtsRepository debtsRepository;

    @InjectMocks
    private GetDebtsUseCase getDebtsUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getDebtsUseCase = new GetDebtsUseCase(debtsRepository);
    }

    @Test
    public void testFindAllDebts() {
        DebtsRequest debtsRequest=new DebtsRequest(BigDecimal.valueOf(5000.00),"dfdsgfdgfbhf14gh");

        List<Debts> debtsList = new ArrayList<>();

        Debts debts=new Debts();
        debts.setDebts(debtsRequest.getDividas());

        debtsList.add(debts);

        when(debtsRepository.findAll()).thenReturn(debtsList);

        List<Debts> result = getDebtsUseCase.findAllDebts();

        assertEquals(1, result.size());

    }
}