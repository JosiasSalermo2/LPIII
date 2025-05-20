package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Comorbidades;
import com.example.scvapi.repository.ComorbidadesRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComorbidadesService 
{
    private ComorbidadesRepository repository;
    public ComorbidadesService(ComorbidadesRepository repository)
    {
        this.repository = repository;
    }

    public List<Comorbidades> getComorbidades()
    {
        return repository.findAll();
    }

    public Optional<Comorbidades> getComorbidadesById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Comorbidades salvar(Comorbidades comorbidades)
    {
        validar(comorbidades);
        return repository.save(comorbidades);
    }

    @Transactional
    public void excluir(Comorbidades comorbidades)
    {
        Objects.requireNonNull(comorbidades.getId());
        repository.delete(comorbidades);
    }

    private void validar(Comorbidades comorbidades)
    {
        //aqui é para validar os campos obrigatórios
    }
}
