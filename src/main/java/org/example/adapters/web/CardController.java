package org.example.adapters.web;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.CardRequest;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.port.CardRepository;
import org.example.core.useCase.address.GetAddressUseCase;
import org.example.core.useCase.card.GetCardUseCase;
import org.example.core.useCase.card.SaveCardUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/adicionarCartao")
@RequiredArgsConstructor
public class CardController {
    private final  SaveCardUseCase saveCardUseCase;
    private final GetCardUseCase getCardUseCase;

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody CardRequest cardRequest){
        try {
            saveCardUseCase.saveCard(cardRequest);
            return new ResponseEntity<>(new ResponseDto("Cartão cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (ConstraintViolationException exception){
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            List<String> errorMessages = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            return new ResponseEntity<>(new ResponseDto(errorMessages), HttpStatus.BAD_REQUEST);
        }catch (DataIntegrityViolationException erro) {
            if (erro.getMessage().contains(" Detalhe: Key (id_externo_cliente)")){
                return new ResponseEntity<>(new ResponseDto("O Cliente ja foi cadastrado "),HttpStatus.BAD_REQUEST);
            } else if (erro.getMessage().contains("Key (nome_cartao)")) {
                return new ResponseEntity<>(new ResponseDto("O cartão já está cadastrado git "),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto("Verifique sua entrada de dados"), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verCard")
    public ResponseEntity getCard(){
        return new ResponseEntity<>(getCardUseCase.findAll(),HttpStatus.OK);
    }

}
