package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EstoqueDTO;
import com.example.scvapi.model.entity.Estoque;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/estoque")
@RequiredArgsConstructor
@CrossOrigin
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
