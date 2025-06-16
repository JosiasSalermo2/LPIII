package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Vacinacao;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinacaoDTO {
    private Long id;
    private String dataAplicacao;
    private Long pacienteId;
    private Long agendamentoId;
    private Long vacinaId;

    public static VacinacaoDTO create(Vacinacao vacinacao) {
        ModelMapper modelMapper = new ModelMapper();
        VacinacaoDTO dto = modelMapper.map(vacinacao, VacinacaoDTO.class);
        dto.pacienteId = vacinacao.getPaciente().getId();
        dto.agendamentoId = vacinacao.getAgendamento().getId();
        return dto;
    }
}
