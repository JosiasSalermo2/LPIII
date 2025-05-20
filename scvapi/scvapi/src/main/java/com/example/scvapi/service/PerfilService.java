package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Usuario;
import com.example.scvapi.repository.UsuarioRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PerfilService {
    private UsuarioRepository repository;

    public PerfilService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> getPerfil(){
        return repository.findAll();
    }

    public Optional<Usuario> getPerfilById(Long id) {
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
        if (usuario.getNome() == null || usuario.getNome().trim().equals("")) {
            throw new RegraNegocioException("Nome inválido");
        }
    }



}
