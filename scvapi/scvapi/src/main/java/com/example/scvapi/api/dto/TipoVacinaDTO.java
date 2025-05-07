package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoVacinaDTO {
    private Long id;
    private String tipoVacina;
    private String descricao;
}
