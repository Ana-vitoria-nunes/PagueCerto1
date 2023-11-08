package org.example.core.useCase.address;

import org.example.core.domain.model.Address;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.port.AddressRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetAddressUseCaseTest {
    @Mock
    private AddressRepository addressRepository;

    private GetAddressUseCase getAddressUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAddressUseCase = new GetAddressUseCase(addressRepository);
    }

    @Test
    public void testFindAll() {
        Address address=new Address();
        address.setId_Address(1L);

        Costumer costumer=new Costumer(1L,"dgfnhgfhnfh284hn","Ana Silva","ana@gmail.com","ana123", LocalDate.of(2004,05,11),"1578596","34997856241",address);

        AddressRequest addressRequest=new AddressRequest(costumer.getExternalId(),"são joão","Noite",500,"MG","Uberaba","14587-025");

        List<Address> addressList = new ArrayList<>();
        Address address1=new Address();
        address1.setCity(addressRequest.getCidade());
        address1.setCep(addressRequest.getCep());
        address1.setStreet(addressRequest.getRuaAvenida());
        address1.setNeighborhood(addressRequest.getBairro());
        address1.setCostumer(costumer);
        address1.setState(addressRequest.getEstado());
        address1.setNumberHouse(addressRequest.getNumeroCasa());

        addressList.add(address1);

        // Configure o comportamento do mock do addressRepository para retornar a lista fictícia
        when(addressRepository.findAll()).thenReturn(addressList);

        // Chame o método findAll do GetAddressUseCase
        List<Address> result = getAddressUseCase.findAll();

        // Verifique se o resultado é o que você espera com base na lista fictícia
        assertEquals(1, result.size());

    }

}