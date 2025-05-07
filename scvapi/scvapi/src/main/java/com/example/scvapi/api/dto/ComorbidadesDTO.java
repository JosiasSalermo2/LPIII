package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComorbidadesDTO {
    private Long id;
    private String comorbidade;
    private String descricao;
    private Long comorbidadeId;
}
