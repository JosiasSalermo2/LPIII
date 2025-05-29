package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FuncionarioDTO;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/funcionario")
@RequiredArgsConstructor
@CrossOrigin
public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public Funcionario converter(FuncionarioDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        return funcionario;
    }
}
