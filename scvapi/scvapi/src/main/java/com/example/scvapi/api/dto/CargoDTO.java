package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {
    private Long id;
    private String cargo;
    private String descricao;
    private Long funcionarioId;
}
