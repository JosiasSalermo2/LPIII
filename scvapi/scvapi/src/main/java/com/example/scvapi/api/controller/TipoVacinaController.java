package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TipoVacinaDTO;
import com.example.scvapi.model.entity.TipoVacina;
import org.modelmapper.ModelMapper;

public class TipoVacinaController
{
    private final TipoVacinaController service;

    public TipoVacina converter(TipoVacinaDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        TipoVacina tipoVacina = modelMapper.map(dto, TipoVacina.class);
        return tipoVacina;
    }
}
