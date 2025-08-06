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
    private Long enderecoId;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoComplemento;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoUf;
    private String enderecoCep;


    public static FabricanteDTO create(Fabricante fabricante) {
        ModelMapper modelMapper = new ModelMapper();
        FabricanteDTO dto = modelMapper.map(fabricante, FabricanteDTO.class);
        dto.telefoneId = fabricante.getTelefone().getId();
        dto.telefoneDDD = fabricante.getTelefone().getDdd();
        dto.telefoneNumero = fabricante.getTelefone().getNumero();
        dto.enderecoId = fabricante.getEndereco().getId();
        dto.enderecoLogradouro = fabricante.getEndereco().getLogradouro();
        dto.enderecoNumero = fabricante.getEndereco().getNumero();
        dto.enderecoComplemento= fabricante.getEndereco().getComplemento();
        dto.enderecoBairro= fabricante.getEndereco().getBairro();
        dto.enderecoCidade= fabricante.getEndereco().getCidade();
        dto.enderecoUf= fabricante.getEndereco().getUf();
        dto.enderecoCep= fabricante.getEndereco().getCep();
        return dto;
    }
}
