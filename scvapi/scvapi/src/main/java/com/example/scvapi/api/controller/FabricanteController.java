package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FabricanteDTO;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.service.FabricanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fabricante")
@RequiredArgsConstructor
@CrossOrigin
public class FabricanteController
{
    private final FabricanteService service;

    public Fabricante converter(FabricanteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Fabricante fabricante = modelMapper.map(dto, Fabricante.class);
        return fabricante;
    }
}
