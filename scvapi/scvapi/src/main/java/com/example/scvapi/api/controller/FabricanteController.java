package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.CompraDTO;
import com.example.scvapi.api.dto.FabricanteDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.service.FabricanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fabricantes")
@RequiredArgsConstructor
@CrossOrigin
public class FabricanteController
{
    private final FabricanteService fabricanteService;

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
