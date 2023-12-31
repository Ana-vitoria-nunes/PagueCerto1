package org.example.adapters.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.CostumerRequest;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.useCase.costumer.GetCostumerUseCase;
import org.example.core.useCase.costumer.GetOnlyCostumer;
import org.example.core.useCase.costumer.SaveCostumerUseCase;
import org.example.core.useCase.costumer.UpdateCostumerUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/cadastro")
@RequiredArgsConstructor
public class CostumerController {
   private final SaveCostumerUseCase saveCostumer;
   private final UpdateCostumerUseCase updateCostumer;
   private final GetCostumerUseCase getCostumer;
   private final GetOnlyCostumer onlyCostumer;
    @PostMapping
    public ResponseEntity addUser(@Valid @RequestBody CostumerRequest cliente) {
        try {
            saveCostumer.saveCostumer(cliente);
            return new ResponseEntity<>(new ResponseDto("Cliente cadastrado com sucesso!!"), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException erro) {
            if (erro.getMessage().contains("  Detalhe: Key (cpf)")){
                return new ResponseEntity<>(new ResponseDto("Cpf já existe"),HttpStatus.BAD_REQUEST);
            } else if (erro.getMessage().contains(" Detalhe: Key (email)")) {
                return new ResponseEntity<>(new ResponseDto("Email já existe"),HttpStatus.BAD_REQUEST);
            } else if (erro.getMessage().contains("Detalhe: Key (name)")) {
                return new ResponseEntity<>(new ResponseDto("Nome completo já existe"),HttpStatus.BAD_REQUEST);
            }else if (erro.getMessage().contains(" Detalhe: Key (phone)")) {
                return new ResponseEntity<>(new ResponseDto("Telefone já existe"), HttpStatus.BAD_REQUEST);
            }
            else if (erro.getMessage().contains("ERROR: value too long for type character varying(15)")) {
                return new ResponseEntity<>(new ResponseDto("O telefone precisa conter apenas 15 numeros"),HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(new ResponseDto("Verifique sua entrada de dados"), HttpStatus.BAD_REQUEST);
        } catch (Exception erro){
            return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{idCostumer}/{escolha}")
    public ResponseEntity updateCostumer(@PathVariable(value = "idCostumer")String id, @RequestBody CostumerRequest costumerRequest, @PathVariable(value = "escolha") String escolha){
        try {
            return new ResponseEntity<>(updateCostumer.updateCostumer(costumerRequest,id,escolha),HttpStatus.OK);
        }catch (Exception erro){
            return new ResponseEntity<>(new ResponseDto(erro.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/data")
    public ResponseEntity getAll(){
        return new ResponseEntity<>(getCostumer.findAll(), HttpStatus.OK);
    }
    @GetMapping("/verCliente")
    public ResponseEntity getCostumer() {
        return new ResponseEntity(onlyCostumer.findCostumer(),HttpStatus.OK);
    }

}
