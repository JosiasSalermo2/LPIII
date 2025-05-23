package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EnderecoService
{
    private EnderecoRepository repository;
    public EnderecoService(EnderecoRepository repository)
    {
        this.repository = repository;
    }

    public List<Endereco> getEndereco()
    {
        return repository.findAll();
    }

    public Optional<Endereco> getEnderecoById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Endereco salvar(Endereco endereco)
    {
        validar(endereco);
        return repository.save(endereco);
    }

    @Transactional
    public void excluir(Endereco endereco)
    {
        Objects.requireNonNull(endereco.getId());
        repository.delete(endereco);
    }

    private void validar(Endereco endereco)
    {
        if (endereco.getCep() == null || endereco.getCep().trim().equals("")) {
            throw new RegraNegocioException("CEP inválido");
        }
        if (endereco.getNumero() == null || endereco.getNumero().trim().equals("")) {
            throw new RegraNegocioException("Número inválido");
        }
    }
}
