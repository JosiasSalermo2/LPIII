package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Lote;
import com.example.scvapi.repository.LoteRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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

    private void validar(Lote lote) {
        if (lote.getDataValidade() == null) {
            throw new RegraNegocioException("Data de validade é obrigatória");
        }
        if (lote.getNumeroLote() == null || lote.getNumeroLote().trim().isEmpty()) {
            throw new RegraNegocioException("Número do lote é obrigatório");
        }
        if (lote.getNumeroAmpola() < 0) {
            throw new RegraNegocioException("Número da ampola não pode ser negativo");
        }
        if (lote.getDosesAmpola() < 0) {
            throw new RegraNegocioException("Doses por ampola não pode ser negativo");
        }
        if (lote.getVacina() == null || lote.getVacina().getId() == null) {
            throw new RegraNegocioException("Vacina inválida");
        }
        if (lote.getEstoque() == null || lote.getEstoque().getId() == null) {
            throw new RegraNegocioException("Estoque inválido");
        }
    }

}
