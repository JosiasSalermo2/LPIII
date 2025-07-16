package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.model.entity.Estoque;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {
    private Long id;
    private String nome;
    private int quantidadeDisponivel;
    private int quantidadeMinima;
    private int quantidadeMaxima;
    private int pontoRessuprimento;
    private Long fabricanteId;
    private String fabricanteNome;


    public static EstoqueDTO create(Estoque estoque) {
        ModelMapper modelMapper = new ModelMapper();
        EstoqueDTO dto = modelMapper.map(estoque, EstoqueDTO.class);

        if (estoque.getFabricante() != null) {
            dto.fabricanteId = estoque.getFabricante().getId();
            dto.fabricanteNome = estoque.getFabricante().getNomeFantasia();
        }

        return dto;
    }
}
