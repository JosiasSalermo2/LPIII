package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.repository.VacinaRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VacinaService
{
    private VacinaRepository repository;
    public VacinaService(VacinaRepository repository)
    {
        this.repository = repository;
    }

    public List<Vacina> getVacina()
    {
        return repository.findAll();
    }

    public Optional<Vacina> getVacinaById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Vacina salvar(Vacina vacina)
    {
        validar(vacina);
        return repository.save(vacina);
    }

    @Transactional
    public void excluir(Vacina vacina)
    {
        Objects.requireNonNull(vacina.getId());
        repository.delete(vacina);
    }

    private void validar(Vacina vacina)
    {
        if (vacina.getVacina() == null || vacina.getVacina().trim().equals("")) {
            throw new RegraNegocioException("Nome da vacina inválido");
        }
        if (vacina.getTipoVacina() == null || vacina.getTipoVacina().trim().equals("")) {
            throw new RegraNegocioException("Tipo da vacina inválido");
        }
    }
}
