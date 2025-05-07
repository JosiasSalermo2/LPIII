package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FabricanteDTO {
    private Long id;
    private String nomeFabricante;
    private String email;
    private String cnpj;
    private String razaoSocial;
}
