package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.LoteDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.*;
import com.example.scvapi.service.CompraService;
import com.example.scvapi.service.EstoqueService;
import com.example.scvapi.service.LoteService;
import com.example.scvapi.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping()
    public ResponseEntity get()
    {
        List<Lote> lotes = loteService.getLote();
        return ResponseEntity.ok(lotes.stream().map(LoteDTO::create).collect(Collectors.toList()));
    }

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

    @PostMapping()
    public ResponseEntity post(@RequestBody LoteDTO dto) {
        try {
            Lote lote = converter(dto);
            lote = loteService.salvar(lote);
            return new ResponseEntity(lote, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody LoteDTO dto) {
        if (!loteService.getLoteById(id).isPresent()) {
            return new ResponseEntity("Lote não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Lote lote = converter(dto);
            lote.setId(id);
            loteService.salvar(lote);
            return ResponseEntity.ok(lote);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Lote> lote = loteService.getLoteById(id);
        if (!lote.isPresent()) {
            return new ResponseEntity("Lote não encontrado.", HttpStatus.NOT_FOUND);
        }
        try {
            loteService.excluir(lote.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
