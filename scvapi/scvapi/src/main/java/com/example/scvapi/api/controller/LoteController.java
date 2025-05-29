package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.LoteDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.model.entity.Lote;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.service.CompraService;
import com.example.scvapi.service.EstoqueService;
import com.example.scvapi.service.LoteService;
import com.example.scvapi.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lotes")
@RequiredArgsConstructor
@CrossOrigin
public class LoteController
{
    private final LoteService loteService;
    private final CompraService compraService;
    private final VacinaService vacinaService;
    private final EstoqueService estoqueService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Lote> lote = loteService.getLoteById(id);
        if (!lote.isPresent())
        {
            return new ResponseEntity("Lote não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(lote.map(LoteDTO::create));
    }

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
