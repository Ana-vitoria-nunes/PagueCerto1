package org.example.core.useCase.costumer;

import org.example.core.domain.model.*;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.domain.model.dto.requestInfoDto.AddressRequestInfoDto;
import org.example.core.domain.model.dto.requestInfoDto.CostumerInfoDto;
import org.example.core.domain.model.dto.requestInfoDto.PaymentInfoDto;
import org.example.core.port.CardRepository;
import org.example.core.useCase.card.GetCardUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetCostumerUseCaseTest {


    @InjectMocks
    private GetCostumerUseCase getCostumerUseCase;

    @Mock
    private CardRepository cardRepository;


    @Test
    public void findAll_shouldReturnAllCustomers() {

        Address address = new Address();
        address.setId_Address(1L);

        AddressRequestInfoDto addressRequestInfoDto = new AddressRequestInfoDto();
        List<PaymentInfoDto> paymentInfoDtos = new ArrayList<>();


        Costumer costumer = new Costumer(1L, "dgfnhgfhnfh284hn", "Ana Silva", "ana@gmail.com", "ana123", LocalDate.of(2004, 05, 11), "1578596", "34997856241", address);
        CostumerInfoDto costumer1 = new CostumerInfoDto(1L, "Ana Silva", "ana@gmail.com", addressRequestInfoDto, paymentInfoDtos);

        List<Card> cardList = new ArrayList<>();
        Card card = new Card();

        card.setCostumer(costumer);
        card.setIdCard(2l);
        cardList.add(card);


        when(cardRepository.findAll()).thenReturn(cardList);

        List<CostumerInfoDto> actualCustomers = getCostumerUseCase.findAll();
        actualCustomers.add(costumer1);

        assertEquals(2, actualCustomers.size());
    }

    @Test
    public void testPutdata() {
        Costumer costumer = new Costumer();
        costumer.setName("Laura");
        costumer.setEmail("laura@gmail.com");
        costumer.setAddress(new Address(1L,"ahyfdvfd5bv455gt",costumer,300,"Avenida Guarapiranga","SP","São Paulo","12345-000","Guarapiranga"));

        List<Debts> debts = new ArrayList<>();
        List<Payment> payments = new ArrayList<>();

        Card card = new Card(1L,"ahyfdvfd5bv436d9",costumer,BigDecimal.valueOf(1000),payments,debts,"02/24","Laura","12345678901","255");
        Payment payment = new Payment(1L,"asdfgrg5bv34",card,debts,LocalDate.of(2024,11,20),Status.CONCLUIDO,2,BigDecimal.valueOf(200),BigDecimal.valueOf(2000), LocalDateTime.now());
        payment.setExternalIdCard(card);
        payments.add(payment);

        CostumerInfoDto costumerInfoDto = getCostumerUseCase.putdata(card);
        costumerInfoDto.setIdInterno(card.getIdCard());
        costumerInfoDto.setEmail(EmailUtils.obscureEmail(costumer.getEmail()));
        costumerInfoDto.setNome(costumer.getName());
        costumerInfoDto.setEndereço(getCostumerUseCase.getAddressInfo(costumer));
        costumerInfoDto.setTransações(getCostumerUseCase.getPayment(card.getIdPayments()));


        assertEquals(1, costumerInfoDto.getIdInterno());
        assertEquals("Laura", costumerInfoDto.getNome());
        assertEquals("l****@gmail.com", costumerInfoDto.getEmail());

        AddressRequestInfoDto addressRequestInfoDto = costumerInfoDto.getEndereço();
        assertNotNull(addressRequestInfoDto);
        assertEquals("Avenida Guarapiranga", addressRequestInfoDto.getRuaAvenida());
        assertEquals("SP", addressRequestInfoDto.getEstado());
        assertEquals("São Paulo", addressRequestInfoDto.getCidade());
        assertEquals("12345-000", addressRequestInfoDto.getCep());
        assertEquals("Guarapiranga", addressRequestInfoDto.getBairro());

        assertEquals(1,payment.getId());
    }

}

