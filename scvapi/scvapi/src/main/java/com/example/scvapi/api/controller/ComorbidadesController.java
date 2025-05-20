package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.ComorbidadesDTO;
import com.example.scvapi.model.entity.Comorbidades;
import com.example.scvapi.service.ComorbidadesService;
import org.modelmapper.ModelMapper;

public class ComorbidadesController {
    private final ComorbidadesService comorbidadesService;

    public Comorbidades converter(ComorbidadesDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Comorbidades comorbidades = modelMapper.map(dto, Comorbidades.class);
        return comorbidades;
    }

}
