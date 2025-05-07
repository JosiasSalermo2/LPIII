package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.model.entity.TipoVacina;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoVacinaDTO {
    private Long id;
    private String tipoVacina;
    private String descricao;

    public static TipoVacinaDTO create(TipoVacina tipoVacina) {
        ModelMapper modelMapper = new ModelMapper();
        TipoVacinaDTO dto = modelMapper.map(tipoVacina, TipoVacinaDTO.class);
        return dto;
    }
}
