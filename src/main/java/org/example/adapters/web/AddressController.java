package org.example.adapters.web;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.core.domain.model.dto.requestDto.AddressRequest;
import org.example.core.domain.model.dto.responseDto.ResponseDto;
import org.example.core.useCase.address.GetAddressUseCase;
import org.example.core.useCase.address.SaveAddressUseCase;
import org.example.core.useCase.address.UpdateAddressUseCase;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class AddressController {

  private final SaveAddressUseCase saveAddressUseCase;
  private final UpdateAddressUseCase updateAddressUseCase;
  private final GetAddressUseCase getAddressUseCase;
  @PostMapping("/adicionarEndereco")
  public ResponseEntity addAddress(@Valid @RequestBody AddressRequest addressRequest){
    try{
      saveAddressUseCase.saveAddress(addressRequest);
        return new ResponseEntity<>( new ResponseDto("Endereço criado"),HttpStatus.CREATED);
     }catch (DataIntegrityViolationException erro ){
        if (erro.getMessage().contains("Detalhe: Key (id_externo_cliente)")){
            return new ResponseEntity<>(new ResponseDto("Esse cliente contém o endereço cadastrado"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseDto("Certifique seus dados"),HttpStatus.INTERNAL_SERVER_ERROR);
    } catch (Exception erro){
      return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/{idAddress}")
  @Transactional(Transactional.TxType.REQUIRED)
  public ResponseEntity updateAddress(@Valid @PathVariable(value = "idAddress" ) String id, @Valid @RequestBody AddressRequest address){
    try {
          updateAddressUseCase.updateAllData(id,address);
         return new ResponseEntity<>(new ResponseDto("Atualizado"),HttpStatus.OK);
     }catch (Exception erro){
      return new ResponseEntity<>(erro.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/verEndereco")
  public ResponseEntity getAddress(){
     return new ResponseEntity<>(getAddressUseCase.findAll(),HttpStatus.OK);
  }
}
