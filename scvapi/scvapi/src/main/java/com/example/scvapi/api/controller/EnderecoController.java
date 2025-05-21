package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EnderecoDTO;
import com.example.scvapi.model.entity.Endereco;
import org.modelmapper.ModelMapper;

public class EnderecoController
{
    private final EnderecoController service;

    public Endereco converter(EnderecoDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        return endereco;
    }
}
