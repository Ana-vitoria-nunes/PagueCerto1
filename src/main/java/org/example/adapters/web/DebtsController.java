package org.example.adapters.web;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.DebtsRequest;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.useCase.debts.GetDebtsUseCase;
import org.example.core.useCase.debts.SaveDebtsUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/divida")
@RequiredArgsConstructor
public class DebtsController {
    private final SaveDebtsUseCase saveDebtsUseCase;
    private final GetDebtsUseCase getDebtsUseCase;
    @PostMapping
    public ResponseEntity saveDebts(@Valid @RequestBody DebtsRequest debtsRequest){
        try {
            saveDebtsUseCase.save(debtsRequest);
            return new ResponseEntity<>(new ResponseDto("Salvo!"), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException erro) {
            if (erro.getMessage().contains("  Detalhe: Key (idDebts)")){
                return new ResponseEntity<>(new ResponseDto("Cpf já existe"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto("Verifique sua entrada de dados"), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/verDividas")
    public ResponseEntity getAddress(){
        return new ResponseEntity<>(getDebtsUseCase.findAllDebts(),HttpStatus.OK);
    }

}
