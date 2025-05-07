package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FornecedorDTO {
    private Long id;
    private String nomeFantasia;
    private String email;
    private String cnpj;
    private String razaoSocial;
}
