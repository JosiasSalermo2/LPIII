package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FuncionarioDTO {
    private String especialidade;
    private String registroConselho;
    private String dataNascimento;
    private Double salario;

}
