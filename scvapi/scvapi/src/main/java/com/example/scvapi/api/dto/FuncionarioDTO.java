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

    public static FuncionarioDTO create(Funcionario funcionario) {
        ModelMapper modelMapper = new ModelMapper();
        FuncionarioDTO dto = modelMapper.map(funcionario, FuncionarioDTO.class);
        dto.cargoId = funcionario.getCargo().getId();
        dto.cargoNome = funcionario.getCargo().getCargo();
        return dto;
    }
}
