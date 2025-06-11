package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.CompraDTO;
import com.example.scvapi.api.dto.EstoqueDTO;
import com.example.scvapi.api.dto.FabricanteDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.service.FabricanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fabricantes")
@RequiredArgsConstructor
@CrossOrigin
public class FabricanteController
{
    private final FabricanteService fabricanteService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Fabricante> fabricantes = fabricanteService.getFabricante();
        return ResponseEntity.ok(fabricantes.stream().map(FabricanteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(id);
        if (!fabricante.isPresent())
        {
            return new ResponseEntity("Fabricante não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(fabricante.map(FabricanteDTO::create));
    }

    public Fabricante converter(FabricanteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Fabricante fabricante = modelMapper.map(dto, Fabricante.class);
        return fabricante;
    }
}
