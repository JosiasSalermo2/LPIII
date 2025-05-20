package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.CargoDTO;
import com.example.scvapi.model.entity.Cargo;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.service.CargoService;
import com.example.scvapi.service.FuncionarioService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class CargoController {
    private final CargoService cargoService;
    private final FuncionarioService funcionarioService;

    public Cargo converter(CargoDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Cargo cargo = modelMapper.map(dto, Cargo.class);
        if (dto.getFuncionarioId() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getFuncionarioId());
            if(!funcionario.isPresent()) {
                cargo.setFuncionario(null);
        }else{
                cargo.setFuncionario(funcionario.get());
            }
        }

        return cargo;
    }
}
