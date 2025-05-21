package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.UsuarioDTO;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.model.entity.Usuario;
import com.example.scvapi.service.FuncionarioService;
import com.example.scvapi.service.UsuarioService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final FuncionarioService funcionarioService;

    public Usuario converter(UsuarioDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        if(dto.getFuncionarioId() != null) {
            Optional<Funcionario> funcionario = funcionarioService.getFuncionarioById(dto.getFuncionarioId());
            if(!funcionario.isPresent()) {
                usuario.setFuncionario(null);
        }else{
                usuario.setFuncionario(funcionario.get());
            }
    }
        return usuario;

}
}

