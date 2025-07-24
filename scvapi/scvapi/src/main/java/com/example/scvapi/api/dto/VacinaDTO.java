package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.model.entity.Vacinacao;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaDTO {
    private Long id;
    private String vacina;
    private String indicacao;
    private String contraIndicacao;
    private int dosesAmpola;
    private Long tipoVacinaId;
    private Long fornecedorId;
    private Long fabricanteId;

    public static VacinaDTO create(Vacina vacina) {
        ModelMapper modelMapper = new ModelMapper();
        VacinaDTO dto = modelMapper.map(vacina, VacinaDTO.class);
        /*
        dto.tipoVacinaId = vacina.getId();
        dto.fornecedorId = vacina.getFornecedor().getId();
        dto.fabricanteId = vacina.getFabricante().getId();
        */

        if (vacina.getTipoVacinaDescricao() != null) {
            dto.setTipoVacinaId(vacina.getTipoVacinaDescricao().getId());
        }

        if (vacina.getFornecedor() != null) {
            dto.setFornecedorId(vacina.getFornecedor().getId());
        }

        if (vacina.getFabricante() != null) {
            dto.setFabricanteId(vacina.getFabricante().getId());
        }

        return dto;


    }
}
