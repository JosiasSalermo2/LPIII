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

    public static FabricanteDTO create(Fabricante fabricante) {
        ModelMapper modelMapper = new ModelMapper();
        FabricanteDTO dto = modelMapper.map(fabricante, FabricanteDTO.class);
        return dto;
    }
}
