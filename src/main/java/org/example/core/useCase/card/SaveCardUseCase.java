package org.example.core.useCase.card;


import lombok.RequiredArgsConstructor;
import org.example.adapters.config.Pass;
import org.example.core.domain.exception.NoItemException;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Costumer;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.port.CardRepository;
import org.example.core.port.CostumerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
@Component
@RequiredArgsConstructor
public class SaveCardUseCase {
    private final CardRepository cardRepository;
    private final CostumerRepository costumerRepository;

    public Card saveCard(CardRequest cardRequest) {
        Card newCard = new Card();

        String idExterno = cardRequest.getIdexternoCliente();
        Costumer costumer = costumerRepository.findByExternalId(idExterno).orElseThrow(() -> new NoItemException("Cliente não encontrado"));

        newCard.generateAndSetExternalIdCartao();
        newCard.setCostumer(costumer);
        newCard.setCostumer(costumer);

        String s = Pass.hashCrypto(cardRequest.getCvv());
        String n = Pass.hashCrypto(cardRequest.getNumeroCartao());

        newCard.setCvv(s);
        newCard.setNumberCard(n);
        dataParseToFormat(cardRequest.getDataDeValidade());
        newCard.setExpiryDate(cardRequest.getDataDeValidade());
        newCard.setNameCostumerCard(cardRequest.getNomeClienteCartao());
        newCard.setCardLimit(cardRequest.getLimiteCartao());


        return cardRepository.save(newCard);
    }

    public LocalDate dataParseToFormat(String dto) {
        DateTimeFormatter dateFormatOutput = DateTimeFormatter.ofPattern("MM/yyyy");

        return YearMonth.parse(dto, dateFormatOutput).atDay(1);
    }


}
