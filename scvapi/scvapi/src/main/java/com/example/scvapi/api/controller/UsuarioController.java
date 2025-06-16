package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.UsuarioDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Funcionario;
import com.example.scvapi.model.entity.Usuario;
import com.example.scvapi.service.FuncionarioService;
import com.example.scvapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {
    private final UsuarioService service;
    private final FuncionarioService funcionarioService;

    @GetMapping()
    public ResponseEntity get(){
        List<Usuario> usuarios = service.getUsuario();
        return ResponseEntity.ok(usuarios.stream().map(UsuarioDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Usuario> usuario = service.getUsuarioById(id);
        if (!usuario.isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuario.map(UsuarioDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = converter(dto);
            usuario = service.salvar(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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

