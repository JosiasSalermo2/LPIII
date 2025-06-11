package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.CompraDTO;
import com.example.scvapi.api.dto.DescarteDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.service.DescarteService;
import com.example.scvapi.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/descartes")
@RequiredArgsConstructor
@CrossOrigin
public class DescarteController
{
    private final DescarteService descarteService;
    private final EstoqueService estoqueService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Descarte> descartes = descarteService.getDescarte();
        return ResponseEntity.ok(descartes.stream().map(DescarteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Descarte> descarte = descarteService.getDescarteById(id);
        if (!descarte.isPresent())
        {
            return new ResponseEntity("Descarte não encontrada.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(descarte.map(DescarteDTO::create));
    }

    public Descarte converter(DescarteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Descarte descarte = modelMapper.map(dto, Descarte.class);
        if (dto.getEstoqueId() != null)
        {
            Optional<Estoque> estoque = estoqueService.getEstoqueById(dto.getEstoqueId());
            if(!estoque.isPresent())
            {
                descarte.setEstoque(null);
            }
            else
            {
                descarte.setEstoque(estoque.get());
            }
        }
        return descarte;
    }
}
