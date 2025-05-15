package com.example.scvapi.service;

import com.example.scvapi.model.entity.Lote;
import com.example.scvapi.repository.LoteRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class LoteService
{
    private LoteRepository repository;
    public LoteService(LoteRepository repository)
    {
        this.repository = repository;
    }

    public List<Lote> getLote()
    {
        return repository.findAll();
    }

    public Optional<Lote> getLoteById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Lote salvar(Lote lote)
    {
        validar(lote);
        return repository.save(lote);
    }

    @Transactional
    public void excluir(Lote lote)
    {
        Objects.requireNonNull(lote.getId());
        repository.delete(lote);
    }

    private void validar(Lote lote)
    {
        //aqui é para validar os campos obrigatórios
    }
}
