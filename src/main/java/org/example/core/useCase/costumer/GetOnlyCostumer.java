package org.example.core.useCase.costumer;

import lombok.RequiredArgsConstructor;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetOnlyCostumer {
    private final CostumerRepository costumerRepository;

    public List findCostumer(){
        return costumerRepository.findAll();
    }
}
