package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacinaDTO {
    private Long id;
    private String vacina;
    private String tipoVacina;
    private String indicacao;
    private String contraIndicacao;
    private int dosesAmpola;
    private Long tipoVacinaDescricaoId;
    private Long fornecedorId;
    private Long fabricanteId;


}
