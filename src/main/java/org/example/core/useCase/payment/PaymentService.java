package org.example.core.useCase.payment;

import lombok.RequiredArgsConstructor;
import org.example.core.domain.exception.NoItemException;
import org.example.core.domain.model.Card;
import org.example.core.domain.model.Debts;
import org.example.core.domain.model.Payment;
import org.example.core.domain.model.Status;
import org.example.core.domain.model.dto.requestDto.DebstRequestPayment;
import org.example.core.domain.model.dto.requestDto.PaymentDto;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.port.CardRepository;
import org.example.core.port.DebtsRepository;
import org.example.core.port.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {
      private final PaymentRepository paymentRepository;
      private final DebtsRepository debtsRepository;
      private final CardRepository cardRepository;
      private double juros = 0;
      private int parcela = 0;
    public Payment insertValueAcordingToDebts(DebstRequestPayment requestPayment, PaymentDto paymentDto) {

        Debts debts = debtsRepository.findByExternalIdDebts(requestPayment.getIdexternoDebts()).orElseThrow(() ->
                new NoItemException("Divida não encontrada"));

        Card card = cardRepository.findByExternalIdCard(requestPayment.getIdexternoCard()).orElseThrow(() ->
                new NoItemException("Cartão não encontrado"));


        Payment paymentMapDto = new Payment();

        switch (requestPayment.getEscolha()) {
            case 1 -> {
                juros = 0.05;
                parcela = 2;
                paymentMapDto.setQuota(parcela);
            }
            case 2 -> {
                juros = 0.07;
                parcela = 4;
                paymentMapDto.setQuota(parcela);
            }
            case 3 -> {
                juros = 0.09;
                parcela = 6;
                paymentMapDto.setQuota(parcela);
            }
            case 4 -> {
                juros = 0.11;
                parcela = 8;
                paymentMapDto.setQuota(parcela);
            }
            case 5 -> {
                juros = 0.13;
                parcela = 10;
                paymentMapDto.setQuota(parcela);
            }
            case 6 -> {
                juros = 0.15;
                parcela = 12;
                paymentMapDto.setQuota(parcela);

            }
            default -> new ResponseDto("Opção inválida, tente novamente");
        }
        BigDecimal valorTotalEmprestimo = debts.getDebts().multiply(BigDecimal.ONE.add(BigDecimal.valueOf(juros)));
        BigDecimal valorTotalParcela = valorTotalEmprestimo.divide(BigDecimal.valueOf(parcela), 2, BigDecimal.ROUND_HALF_UP);

        paymentMapDto.generateAndSetExternalIdPayment();
        paymentDto.setValorTotalParcela(valorTotalParcela);
        paymentDto.setValorTotalEmprestimo(valorTotalEmprestimo);

        paymentMapDto.setExternalIdCard(card);
        paymentMapDto.setTotalLending(paymentDto.getValorTotalEmprestimo());
        paymentMapDto.setTotalQuota(paymentDto.getValorTotalParcela());
        paymentMapDto.setDayDebts(paymentMapDto.getDayDebts());
        
        LocalDate dateToday = LocalDate.now();
        LocalDate datePaymentChoose = requestPayment.getDatePaymentChoose();

        if (dateToday.getMonthValue() != datePaymentChoose.getMonthValue()){
            paymentMapDto.setStats(Status.PENDENTE);
        } else {
            paymentMapDto.setStats(Status.CONCLUIDO);
        }
        paymentMapDto.setDayMoth(requestPayment.getDatePaymentChoose());

        paymentRepository.save(paymentMapDto);

        debts.setExternalIdPayment(paymentMapDto);
        debtsRepository.save(debts);

        return paymentMapDto;
    }

}

