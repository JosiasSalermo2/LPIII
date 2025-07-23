package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Usuario;
import com.example.scvapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getUsuario(){
        return repository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validar(usuario);
        return repository.save(usuario);
    }

    @Transactional
    public void excluir(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
    }

    private void validar(Usuario usuario) {
        if (usuario.getLogin() == null || usuario.getLogin().equals("")) {
            throw new RegraNegocioException("Login inválido");
        }
        if (usuario.getCpf() == null || usuario.getCpf().equals("")) {
            throw new RegraNegocioException("CPF inválido");
        }

    }



}
