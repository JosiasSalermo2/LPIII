package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private Long id;
    private String perfil;
    private String acessos;
    private Long funcionarioId;
}
