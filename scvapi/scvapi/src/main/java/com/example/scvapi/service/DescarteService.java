package com.example.scvapi.service;

import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.repository.DescarteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DescarteService
{
    private DescarteRepository repository;
    public DescarteService(DescarteRepository descarteRepository)
    {
        this.repository = descarteRepository;
    }

    public List<Descarte> getDescarte()
    {
        return repository.findAll();
    }

    public Optional<Descarte> getDescarteById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Descarte salvar(Descarte descarte)
    {
        validar(descarte);
        return repository.save(descarte);
    }

    @Transactional
    public void excluir(Descarte descarte)
    {
        Objects.requireNonNull(descarte.getId());
        repository.delete(descarte);
    }

    private void validar(Descarte descarte)
    {
        //aqui é para validar os campos obrigatórios
    }
}
