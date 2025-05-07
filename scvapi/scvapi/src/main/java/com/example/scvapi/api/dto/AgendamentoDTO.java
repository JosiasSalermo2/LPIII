package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private String dataAgendamento;
    private String horaAgendamento;
    private Long pacienteId;
    private Long vacinacaoId;

}
