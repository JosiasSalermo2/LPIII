package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EstoqueService
{
    private EstoqueRepository repository;
    public EstoqueService(EstoqueRepository repository)
    {
        this.repository = repository;
    }

    public List<Estoque> getEstoque()
    {
        return repository.findAll();
    }

    public Optional<Estoque> getEstoqueById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Estoque salvar(Estoque estoque)
    {
        validar(estoque);
        return repository.save(estoque);
    }

    @Transactional
    public void excluir(Estoque estoque)
    {
        Objects.requireNonNull(estoque.getId());
        repository.delete(estoque);
    }

    private void validar(Estoque estoque)
    {
        //aqui é para validar os campos obrigatórios
    }
}
