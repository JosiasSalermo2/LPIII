package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteDTO {
    private Long id;
    private String dataVacina;
    private String numeroLote;
    private int numeroVacinas;
    private int dosesAmpola;
    private Long compraId;
    private Long vacinaId;
    private Long estoqueId;

}
