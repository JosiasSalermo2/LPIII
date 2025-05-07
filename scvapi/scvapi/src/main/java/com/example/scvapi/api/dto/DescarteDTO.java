package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescarteDTO {
    private Long id;
    private String nome;
    private String fabricante;
    private int quantidadeDescartes;
    private Long estoqueId;
}
