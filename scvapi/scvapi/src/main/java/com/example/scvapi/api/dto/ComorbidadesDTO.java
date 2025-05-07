package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Comorbidades;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComorbidadesDTO {
    private Long id;
    private String comorbidade;
    private String descricao;
    private Long comorbidadeId;

    public static ComorbidadesDTO create(Comorbidades comorbidades) {
        ModelMapper modelMapper = new ModelMapper();
        ComorbidadesDTO dto = modelMapper.map(comorbidades, ComorbidadesDTO.class);
        return dto;
    }
}
