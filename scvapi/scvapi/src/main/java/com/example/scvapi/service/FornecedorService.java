package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.repository.FornecedorRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FornecedorService
{
    private FornecedorRepository repository;
    public FornecedorService(FornecedorRepository repository)
    {
        this.repository = repository;
    }

    public List<Fornecedor> getFornecedor()
    {
        return repository.findAll();
    }

    public Optional<Fornecedor> getFornecedorById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Fornecedor salvar(Fornecedor fornecedor)
    {
        validar(fornecedor);
        return repository.save(fornecedor);
    }

    @Transactional
    public void excluir(Fornecedor fornecedor)
    {
        Objects.requireNonNull(fornecedor.getId());
        repository.delete(fornecedor);
    }

    private void validar(Fornecedor fornecedor)
    {
        if (fornecedor.getNomeFantasia() == null || fornecedor.getNomeFantasia().trim().equals(""))
        {
            throw new RegraNegocioException("Nome inválido");
        }
        if (fornecedor.getEmail() == null || fornecedor.getEmail().trim().equals(""))
        {
            throw new RegraNegocioException("E-mail inválido");
        }
        if (fornecedor.getCnpj() == null || fornecedor.getCnpj().trim().equals(""))
        {
            throw new RegraNegocioException("CNPJ inválido");
        }
    }
}
