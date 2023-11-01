package org.example.core.useCase.address;

import lombok.RequiredArgsConstructor;
import org.example.core.port.AddressRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAddressUseCase {

    private final AddressRepository addressRepository;

    public List findAll(){
        return addressRepository.findAll();
    }

}
