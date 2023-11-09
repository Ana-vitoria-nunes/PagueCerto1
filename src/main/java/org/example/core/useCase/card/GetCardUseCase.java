package org.example.core.useCase.card;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.domain.model.dto.requestInfoDto.CardInfoDto;
import org.example.core.port.CardRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetCardUseCase {
    private final CardRepository cardRepository;
    public List<CardInfoDto> findAll(){
       return cardRepository.findAll().stream().map(card -> putdata(card)).collect(Collectors.toList());
    }
    public CardInfoDto putdata(Card card) {
        return CardInfoDto.builder()
                .idexternoCard(card.getExternalIdCard())
                .nomeClienteCartao(card.getNameCostumerCard())
                .numeroCartao(card.getNumberCard())
                .cvv(card.getCvv())
                .dataDeValidade(card.getExpiryDate())
                .limiteCartao(card.getCardLimit())
                .build();
    }

}
