package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Cargo;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.model.entity.Descarte;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescarteDTO {
    private Long id;
    private String nome;
    private int quantidadeDescartes;
    private Long estoqueId;
    private String estoqueNome;

    public static DescarteDTO create(Descarte descarte) {
        ModelMapper modelMapper = new ModelMapper();
        DescarteDTO dto = modelMapper.map(descarte, DescarteDTO.class);

        dto.estoqueId = descarte.getEstoque().getId();
        dto.estoqueNome = descarte.getEstoque().getNome();
        dto.nome = descarte.getNome();
        dto.quantidadeDescartes = descarte.getQuantidadeDescarte();

        return dto;
    }
}
