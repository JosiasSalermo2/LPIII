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
    private Long enderecoId;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoComplemento;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoUf;
    private String enderecoCep;

    public static FornecedorDTO create(Fornecedor fornecedor) {
        ModelMapper modelMapper = new ModelMapper();
        FornecedorDTO dto = modelMapper.map(fornecedor, FornecedorDTO.class);
        dto.telefoneId = fornecedor.getTelefone().getId();
        dto.telefoneDDD = fornecedor.getTelefone().getDdd();
        dto.telefoneNumero = fornecedor.getTelefone().getNumero();
        dto.enderecoId = fornecedor.getEndereco().getId();
        dto.enderecoLogradouro = fornecedor.getEndereco().getLogradouro();
        dto.enderecoNumero = fornecedor.getEndereco().getNumero();
        dto.enderecoComplemento= fornecedor.getEndereco().getComplemento();
        dto.enderecoBairro= fornecedor.getEndereco().getBairro();
        dto.enderecoCidade= fornecedor.getEndereco().getCidade();
        dto.enderecoUf= fornecedor.getEndereco().getUf();
        dto.enderecoCep= fornecedor.getEndereco().getCep();
        return dto;
    }
}
