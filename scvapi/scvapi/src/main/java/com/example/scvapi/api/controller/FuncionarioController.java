package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FuncionarioDTO;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.service.FuncionarioService;
import org.modelmapper.ModelMapper;

public class FuncionarioController {
    private final FuncionarioService funcionarioService;

    public Funcionario converter(FuncionarioDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        return funcionario;
    }
}
