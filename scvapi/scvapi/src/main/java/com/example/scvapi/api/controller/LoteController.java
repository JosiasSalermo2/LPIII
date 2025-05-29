package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.LoteDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.model.entity.Lote;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.service.CompraService;
import com.example.scvapi.service.EstoqueService;
import com.example.scvapi.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lote")
@RequiredArgsConstructor
@CrossOrigin
public class LoteController
{
    private final CompraService compraService;
    private final VacinaService vacinaService;
    private final EstoqueService estoqueService;

    public Lote converter(LoteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Lote lote = modelMapper.map(dto, Lote.class);
        if (dto.getCompraId() != null)
        {
            Optional<Compra> compra = compraService.getCompraById(dto.getCompraId());
            if(!compra.isPresent())
            {
                lote.setCompra(null);
            }
            else
            {
                lote.setCompra(compra.get());
            }
        }
        if (dto.getVacinaId() != null)
        {
            Optional<Vacina> vacina = vacinaService.getVacinaById(dto.getVacinaId());
            if(!vacina.isPresent())
            {
                lote.setVacina(null);
            }
            else
            {
                lote.setVacina(vacina.get());
            }
        }
        if (dto.getEstoqueId() != null)
        {
            Optional<Estoque> estoque = estoqueService.getEstoqueById(dto.getEstoqueId());
            if(!estoque.isPresent())
            {
                lote.setEstoque(null);
            }
            else
            {
                lote.setEstoque(estoque.get());
            }
        }
        return lote;
    }
}
