package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoDTO {
    private Long id;
    private String dataAgendamento;
    private String horaAgendamento;
    private Long pacienteId;
    private String pacienteNome;
    private Long vacinacaoId;

    public static AgendamentoDTO create(Agendamento agendamento) {
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.pacienteNome = agendamento.getPaciente().getNome();
        dto.vacinacaoId = agendamento.getVacinacao().getId();
        return dto;
    }
}
