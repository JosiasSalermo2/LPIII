package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.model.entity.Funcionario;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private String dataNascimento;
    private String dataAdmissao;
    private String especialidade;
    private String registroConselho;
    private Double salario;

    public static FuncionarioDTO create(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        return dto;
    }
}
