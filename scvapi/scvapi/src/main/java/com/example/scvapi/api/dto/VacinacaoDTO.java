package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinacaoDTO {
    private Long id;
    private String dataVacinacao;
    private Long pacienteId;
    private Long agendamentoId;
}
