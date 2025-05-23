package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.repository.TelefoneRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TelefoneService
{
    private TelefoneRepository repository ;
    public TelefoneService(TelefoneRepository repository)
    {
        this.repository = repository;
    }

    public List<Telefone> getTelefone()
    {
        return repository.findAll();
    }

    public Optional<Telefone> getTelefoneById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Telefone salvar(Telefone telefone)
    {
        validar(telefone);
        return repository.save(telefone);
    }

    @Transactional
    public void excluir(Telefone telefone)
    {
        Objects.requireNonNull(telefone.getId());
        repository.delete(telefone);
    }

    private void validar(Telefone telefone)
    {
        if (telefone.getDdd() == null || telefone.getDdd().trim().equals(""))
        {
            throw new RegraNegocioException("DDD inválido");
        }
        if (telefone.getNumero() == null || telefone.getNumero().trim().equals(""))
        {
            throw new RegraNegocioException("Número inválido");
        }
    }
}
