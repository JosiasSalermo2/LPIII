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
    private String fabricante;
    private int quantidadeDisponivel;
    private int quantidadeMinima;
    private String dataValidade;
    private Long vacinacaoId;
    private Long descarteId;
    private Long funcionarioId;

    public static EstoqueDTO create(Estoque estoque) {
        ModelMapper modelMapper = new ModelMapper();
        EstoqueDTO dto = modelMapper.map(estoque, EstoqueDTO.class);
        return dto;
    }
}
