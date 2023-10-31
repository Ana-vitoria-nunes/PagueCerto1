package org.example.core.useCase.costumer;

import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.CostumerRequest;
import org.example.core.port.CostumerRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveCostumerUseCase {

    private final CostumerRepository costumerRepository;

    public Costumer saveCostumer(CostumerRequest dtoCliente) {
            Costumer costumer = new Costumer();

            String s = Pass.hashCrypto(dtoCliente.getSenha());
            dtoCliente.setSenha(s);

            costumer.setPassword(dtoCliente.getSenha());
            costumer.generateAndSetExternalId();
            costumer.setPassword(dtoCliente.getSenha());
            costumer.setEmail(dtoCliente.getEmail());
            costumer.setCpf(dtoCliente.getCpf());
            costumer.setName(dtoCliente.getNomeCompleto());
            costumer.setBirthDate(dtoCliente.getDataNascimento());
            costumer.setPhone(dtoCliente.getTelefone());

        costumerRepository.save(costumer);
            return costumer;
    }
}
