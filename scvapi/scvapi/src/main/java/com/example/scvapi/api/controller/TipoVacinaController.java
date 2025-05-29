package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TipoVacinaDTO;
import com.example.scvapi.model.entity.TipoVacina;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tipo-vacina")
@RequiredArgsConstructor
@CrossOrigin
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
