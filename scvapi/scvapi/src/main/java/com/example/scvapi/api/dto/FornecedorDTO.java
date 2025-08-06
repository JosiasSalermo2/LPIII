package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.model.entity.Fornecedor;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long id;
    private String nomeFantasia;
    private String email;
    private String cnpj;
    private String razaoSocial;
    private Long telefoneId;
    private String telefoneDDD;
    private String telefoneNumero;

    public static FornecedorDTO create(Fornecedor fornecedor) {
        ModelMapper modelMapper = new ModelMapper();
        FornecedorDTO dto = modelMapper.map(fornecedor, FornecedorDTO.class);
        dto.telefoneId = fornecedor.getTelefone().getId();
        dto.telefoneDDD = fornecedor.getTelefone().getDdd();
        dto.telefoneNumero = fornecedor.getTelefone().getNumero();
        return dto;
    }
}
