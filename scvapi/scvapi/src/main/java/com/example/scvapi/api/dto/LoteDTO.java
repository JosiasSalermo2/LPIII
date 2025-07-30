package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Lote;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoteDTO {
    private Long id;
    private String dataValidade;
    private String numeroLote;
    private int numeroAmpola;
    private int dosesAmpola;
    private Long compraId;
    private Long vacinaId;
    private String vacina;
    private Long estoqueId;
    private String estoqueNome;

    public static LoteDTO create(Lote lote) {
        ModelMapper modelMapper = new ModelMapper();
        LoteDTO dto = modelMapper.map(lote, LoteDTO.class);
        dto.compraId = lote.getCompra().getId();
        dto.vacinaId = lote.getVacina().getId();
        dto.vacina = lote.getVacina().getVacina();
        dto.estoqueId = lote.getEstoque().getId();
        dto.estoqueNome = lote.getEstoque().getNome();
        return dto;
    }

}
