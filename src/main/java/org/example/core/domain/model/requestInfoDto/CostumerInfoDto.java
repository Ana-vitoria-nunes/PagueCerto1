package org.example.core.domain.model.requestInfoDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.core.domain.model.dto.requestInfoDto.AddressRequestInfoDto;
import org.example.core.domain.model.dto.requestInfoDto.PaymentInfoDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostumerInfoDto {
    private Long idInterno;
    private String nome;
    private String email; // email ofuscado
    AddressRequestInfoDto endereço;
    List<PaymentInfoDto> transações;
}
