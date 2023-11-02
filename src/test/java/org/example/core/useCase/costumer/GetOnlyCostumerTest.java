package org.example.core.useCase.costumer;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.CostumerRequest;
import org.example.core.port.CostumerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetOnlyCostumerTest {
    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private GetOnlyCostumer getOnlyCostumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getOnlyCostumer = new GetOnlyCostumer(costumerRepository);
    }

    @Test
    public void testFindCostumer() {
        Address address=new Address();
        CostumerRequest costumerRequest=new CostumerRequest("Ana Silva", LocalDate.of(2004,05,14),"ana@gmail.com","ana123","47859612348","33997584126","a**@gmail.com");
        List<Costumer> costumerList = new ArrayList<>();
        Costumer costumer=new Costumer();
        costumer.setName(costumerRequest.getNomeCompleto());
        costumer.setPhone(costumerRequest.getTelefone());
        costumer.setCpf(costumerRequest.getCpf());
        costumer.setEmail(costumerRequest.getEmail());
        costumer.setPassword(costumerRequest.getSenha());
        costumer.setAddress(address);
        costumer.setBirthDate(costumerRequest.getDataNascimento());
        costumerList.add(costumer);


        when(costumerRepository.findAll()).thenReturn(costumerList);

        List<Costumer> result = getOnlyCostumer.findCostumer();

        assertEquals(1, result.size());

    }

}