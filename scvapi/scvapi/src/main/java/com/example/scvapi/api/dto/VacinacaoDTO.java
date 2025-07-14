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
    private String pacienteNome;
    private String tipoSanguineo;
    private String alergia;
    private String medicamentosRegulares;

    private Long agendamentoId;
    private Long estoqueId;
    private String estoqueNome;


    public static VacinacaoDTO create(Vacinacao vacinacao) {
        ModelMapper modelMapper = new ModelMapper();
        VacinacaoDTO dto = modelMapper.map(vacinacao, VacinacaoDTO.class);

        dto.pacienteId = vacinacao.getPaciente().getId();
        dto.agendamentoId = vacinacao.getAgendamento().getId();
        dto.estoqueId = vacinacao.getEstoque().getId();

        dto.pacienteNome = vacinacao.getPaciente().getNome();
        dto.tipoSanguineo = vacinacao.getPaciente().getTipoSanguineo();
        dto.alergia = vacinacao.getPaciente().getAlergia();
        dto.medicamentosRegulares = vacinacao.getPaciente().getMedicamentosRegulares();
        dto.estoqueNome = vacinacao.getEstoque().getNome();
        return dto;
    }
}
