package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
    private Long id;
    private double valor;
    private String dataCompra;
    private Long fornecedorId;
    private Long fabricanteId;
}
