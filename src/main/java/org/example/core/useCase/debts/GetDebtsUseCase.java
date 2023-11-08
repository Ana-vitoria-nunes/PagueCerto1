package org.example.core.useCase.debts;

import lombok.RequiredArgsConstructor;
import org.example.core.port.DebtsRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetDebtsUseCase {
    private final DebtsRepository debtsRepository;

    public List findAllDebts(){
        return debtsRepository.findAll();
    }

}
