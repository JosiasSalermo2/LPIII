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
    private String horarioAgendamento;
    private Long pacienteId;
    private String pacienteNome;
    private Long vacinaId;
    private String vacina;


    public static AgendamentoDTO create(Agendamento agendamento) {
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.pacienteNome = agendamento.getPaciente().getNome();
        dto.pacienteId = agendamento.getPaciente().getId();
        dto.vacinaId = agendamento.getVacina().getId();
        dto.vacina = agendamento.getVacina().getVacina();

        return dto;
    }
}
