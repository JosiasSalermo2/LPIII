package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.model.entity.Fabricante;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FabricanteDTO {
    private Long id;
    private String nomeFantasia;
    private String email;
    private String cnpj;
    private String razaoSocial;
    private Long telefoneId;
    private String telefoneDDD;
    private String telefoneNumero;

    public static FabricanteDTO create(Fabricante fabricante) {
        ModelMapper modelMapper = new ModelMapper();
        FabricanteDTO dto = modelMapper.map(fabricante, FabricanteDTO.class);
        dto.telefoneId = fabricante.getTelefone().getId();
        dto.telefoneDDD = fabricante.getTelefone().getDdd();
        dto.telefoneNumero = fabricante.getTelefone().getNumero();
        return dto;
    }
}
