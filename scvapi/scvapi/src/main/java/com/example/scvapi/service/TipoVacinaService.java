package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.TipoVacina;
import com.example.scvapi.repository.TipoVacinaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TipoVacinaService
{
    private TipoVacinaRepository repository;
    public TipoVacinaService(TipoVacinaRepository repository)
    {
        this.repository = repository;
    }

    public List<TipoVacina> getTipoVacina()
    {
        return repository.findAll();
    }

    public Optional<TipoVacina> getTipoVacinaById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public TipoVacina salvar(TipoVacina tipoVacina)
    {
        validar(tipoVacina);
        return repository.save(tipoVacina);
    }

    @Transactional
    public void excluir(TipoVacina tipoVacina)
    {
        Objects.requireNonNull(tipoVacina.getId());
        repository.delete(tipoVacina);
    }

    private void validar(TipoVacina tipoVacina)
    {
        if (tipoVacina.getTipoVacina() == null || tipoVacina.getTipoVacina().trim().equals("")) {
            throw new RegraNegocioException("Tipo de vacina inválido");
        }
    }
}
