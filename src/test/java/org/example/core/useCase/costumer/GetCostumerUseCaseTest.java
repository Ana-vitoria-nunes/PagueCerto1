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

//    @Test
//    public void findAll_shouldReturnAllCustomers() {
//        AddressRequestInfoDto addressRequestInfoDto = new AddressRequestInfoDto();
//        List<PaymentInfoDto> paymentInfoDtos = new ArrayList<>();
//        CostumerInfoDto costumer1 = new CostumerInfoDto(1l,"laura","laura@gmail.com",addressRequestInfoDto,paymentInfoDtos);
//
//        CostumerInfo costumer2 = new CostumerInfo(2, "Name2", "Email2");
//        List<CostumerInfo> costumers = Arrays.asList(costumer1, costumer2);
//
//        CostumerInfoDto costumerInfoDto1 = new CostumerInfoDto(1, "Name1", "Email1");
//        CostumerInfoDto costumerInfoDto2 = new CostumerInfoDto(2, "Name2", "Email2");
//        List<CostumerInfoDto> costumerInfoDtos = Arrays.asList(costumerInfoDto1, costumerInfoDto2);
//
//        // when
//        when(cardRepository.findAll()).thenReturn(costumers);
//
//        // then
//        List<CostumerInfoDto> actualCustomers = cardService.findAll();
//        assertEquals(costumerInfoDtos, actualCustomers);
//    }
@Test
public void findAll_shouldReturnAllCustomers() {

    Address address = new Address();
    address.setId_Address(1L);

    AddressRequestInfoDto addressRequestInfoDto = new AddressRequestInfoDto();
    List<PaymentInfoDto> paymentInfoDtos = new ArrayList<>();


    Costumer costumer = new Costumer(1L,"dgfnhgfhnfh284hn","Ana Silva","ana@gmail.com","ana123", LocalDate.of(2004,05,11),"1578596","34997856241",address);
    CostumerInfoDto costumer1 = new CostumerInfoDto(1L,"Ana Silva","ana@gmail.com",addressRequestInfoDto,paymentInfoDtos);

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
}

//    @Test
//    public void testFindAll() {
//        CardRequest cardRequest = new CardRequest("ahyfdvfd5bv436d9","Ana Silva","7994561237895","548","15/09/2034", BigDecimal.valueOf(1500));
//        Address address = new Address();
//        address.setId_Address(1L);
//
//        Costumer costumer = new Costumer(1L,"dgfnhgfhnfh284hn","Ana Silva","ana@gmail.com","ana123", LocalDate.of(2004,05,11),"1578596","34997856241",address);
//
//        List<Debts> debtsList = new ArrayList<>();
//
//        List<Card> cardList = new ArrayList<>();
//        Card card = new Card();
//        card.setCardLimit(cardRequest.getLimiteCartao());
//        card.setExpiryDate(cardRequest.getDataDeValidade());
//        card.setNumberCard(cardRequest.getNumeroCartao());
//        card.setCvv(cardRequest.getCvv());
//        card.setNameCostumerCard(cardRequest.getNomeClienteCartao());
//        card.setCostumer(costumer);
//        card.setDebts(debtsList);
//        cardList.add(card);
//
//        when(cardRepository.findAll()).thenReturn(cardList);
//        List<CardRequest> cardRequests = getCardUseCase.findAll();
//        assertEquals(1, cardRequests.size());
//    }
//
//    @Test
//    public void testPutdata() {
////        // Mock data
////        Card card = new Card(1, new Costumer("John Doe", "johndoe@example.com", ...), ...);
////
////        // Perform the test
////        CostumerInfoDto costumerInfoDto = getCostumerUseCase.putdata(card);
////
////        // Assertions
////        assertEquals(1, costumerInfoDto.getIdInterno());
////        assertEquals("John Doe", costumerInfoDto.getNome());
////        // Add more assertions for the fields in CostumerInfoDto as needed.
//    }
//
//    @Test
//    public void testGetPayment() {
//        // Mock data
////        Payment payment1 = new Payment(...);
////        Payment payment2 = new Payment(...);
////        List<Payment> payments = Arrays.asList(payment1, payment2);
////
////        // Perform the test
////        List<PaymentInfoDto> paymentInfoDtos = getCostumerUseCase.getPayment(payments);
////
////        // Assertions
////        assertEquals(2, paymentInfoDtos.size());
////        // Add assertions for the fields in PaymentInfoDto as needed.
//    }
//
//    @Test
//    public void testGetPaymentWithEmptyList() {
////        // Mock data
////        List<Payment> emptyPayments = Collections.emptyList();
////
////        // Perform the test
////        List<PaymentInfoDto> paymentInfoDtos = getCostumerUseCase.getPayment(emptyPayments);
////
////        // Assertions
////        assertTrue(paymentInfoDtos.isEmpty());
//    }
//
//    @Test
//    public void testGetAddressInfo() {
////        // Mock data
////        Costumer costumer = new Costumer("John Doe", "johndoe@example.com", ...);
////
////        // Perform the test
////        AddressRequestInfoDto addressRequestInfoDto = getCostumerUseCase.getAddressInfo(costumer);
////
////        // Assertions
////        assertEquals("John Doe", addressRequestInfoDto.getRuaAvenida());
////        // Add assertions for other fields in AddressRequestInfoDto as needed.
//    }
//}