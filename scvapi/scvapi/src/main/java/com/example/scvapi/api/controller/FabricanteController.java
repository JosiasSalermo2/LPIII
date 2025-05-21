package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FabricanteDTO;
import com.example.scvapi.model.entity.Fabricante;
import org.modelmapper.ModelMapper;

public class FabricanteController
{
    private final FabricanteController service;

    public Fabricante converter(FabricanteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Fabricante fabricante = modelMapper.map(dto, Fabricante.class);
        return fabricante;
    }
}
