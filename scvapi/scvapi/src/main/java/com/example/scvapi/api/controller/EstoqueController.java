package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EstoqueDTO;
import com.example.scvapi.model.entity.Estoque;
import org.modelmapper.ModelMapper;

public class EstoqueController
{
    private final EstoqueController service;

    public Estoque converter(EstoqueDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Estoque estoque = modelMapper.map(dto, Estoque.class);
        return estoque;
    }
}
