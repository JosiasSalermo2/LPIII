package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TelefoneDTO;
import com.example.scvapi.model.entity.Telefone;
import org.modelmapper.ModelMapper;

public class TelefoneController {

    public Telefone converter(TelefoneDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Telefone telefone = modelMapper.map(dto, Telefone.class);
        return telefone;
    }
}
