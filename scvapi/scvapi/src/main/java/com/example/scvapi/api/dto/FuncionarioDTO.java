package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.model.entity.Funcionario;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private Long id;
    private String nome;    
    private String cpf;
    private String email;
    private String rg;
    private String dataNascimento;
    private String dataAdmissao;
    private String especialidade;
    private String registroConselho;
    private Double salario;
    private Long cargoId;
    private String cargoNome;
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

    public static FuncionarioDTO create(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        dto.cargoId = funcionario.getCargo().getId();
        dto.cargoNome = funcionario.getCargo().getCargo();
        dto.telefoneId = funcionario.getTelefone().getId();
        dto.telefoneDDD = funcionario.getTelefone().getDdd();
        dto.telefoneNumero = funcionario.getTelefone().getNumero();
        dto.enderecoId = funcionario.getEndereco().getId();
        dto.enderecoLogradouro = funcionario.getEndereco().getLogradouro();
        dto.enderecoNumero = funcionario.getEndereco().getNumero();
        dto.enderecoComplemento = funcionario.getEndereco().getComplemento();
        dto.enderecoBairro = funcionario.getEndereco().getBairro();
        dto.enderecoCidade = funcionario.getEndereco().getCidade();
        dto.enderecoUf = funcionario.getEndereco().getUf();
        dto.enderecoCep = funcionario.getEndereco().getCep();
        return dto;
    }
}
