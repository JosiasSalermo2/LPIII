package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TelefoneDTO;
import com.example.scvapi.model.entity.Telefone;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/telefone")
@RequiredArgsConstructor
@CrossOrigin
public class TelefoneController {

    public Telefone converter(TelefoneDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Telefone telefone = modelMapper.map(dto, Telefone.class);
        return telefone;
    }
}
