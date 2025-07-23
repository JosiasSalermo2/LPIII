package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Compra;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompraDTO {
    private Long id;
    private double valor;
    private String dataCompra;
    private Long fornecedorId;
    private String fornecedorCNPJ;
    private String fornecedorNome;
    private String fabricanteCNPJ;
    private Long fabricanteId;
    private String fabricanteNome;
    private Long vacinaId;
    private String vacinaNome;

    public static CompraDTO create(Compra compra) {
        ModelMapper modelMapper = new ModelMapper();
        CompraDTO dto = modelMapper.map(compra, CompraDTO.class);
        dto.fornecedorCNPJ = compra.getFornecedor().getCnpj();
        dto.fornecedorNome = compra.getFornecedor().getNomeFantasia();
        dto.fabricanteCNPJ = compra.getFornecedor().getCnpj();
        dto.fabricanteNome = compra.getFabricante().getNomeFantasia();
        dto.vacinaId = compra.getVacina().getId();
        dto.vacinaNome = compra.getVacina().getVacina();
        return dto;
    }
}
