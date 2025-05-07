package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO {
    private Long id;
    private String nome;
    private String fabricante;
    private int quantidadeDisponivel;
    private int quantidadeMinima;
    private String dataValidade;
    private Long vacinacaoId;
    private Long descarteId;
    private Long funcionarioId;
}
