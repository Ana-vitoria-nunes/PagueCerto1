package org.example.core.domain.model.dto.requestInfoDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardInfoDto {

    private String idexternoCard;
    private String nomeClienteCartao;
    private String numeroCartao;
    private String cvv;

    @JsonFormat(pattern = "MM/yyyy")
    @NotNull
    private String dataDeValidade;

    @Column(nullable = false)
    private BigDecimal limiteCartao;

}
