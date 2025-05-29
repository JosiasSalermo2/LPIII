package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.UsuarioDTO;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.model.entity.Usuario;
import com.example.scvapi.service.FuncionarioService;
import com.example.scvapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
@CrossOrigin
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

