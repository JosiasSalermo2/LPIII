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


    public static AgendamentoDTO create(Agendamento agendamento) {
        ModelMapper modelMapper = new ModelMapper();
        AgendamentoDTO dto = modelMapper.map(agendamento, AgendamentoDTO.class);
        dto.pacienteNome = agendamento.getPaciente().getNome();

        return dto;
    }
}
