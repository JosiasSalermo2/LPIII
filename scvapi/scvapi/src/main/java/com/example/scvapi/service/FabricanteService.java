package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.repository.FabricanteRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FabricanteService
{
    private FabricanteRepository repository;
    public FabricanteService(FabricanteRepository repository)
    {
        this.repository = repository;
    }

    public List<Fabricante> getFabricante()
    {
        return repository.findAll();
    }

    public Optional<Fabricante> getFabricanteById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Fabricante salvar(Fabricante fabricante)
    {
        validar(fabricante);
        return repository.save(fabricante);
    }

    @Transactional
    public void excluir(Fabricante fabricante)
    {
        Objects.requireNonNull(fabricante.getId());
        repository.delete(fabricante);
    }

    private void validar(Fabricante fabricante)
    {
        if (fabricante.getNomeFantasia() == null || fabricante.getNomeFantasia().trim().equals(""))
        {
            throw new RegraNegocioException("Nome inválido");
        }
        if (fabricante.getEmail() == null || fabricante.getEmail().trim().equals(""))
        {
            throw new RegraNegocioException("E-mail inválido");
        }
        if (fabricante.getCnpj() == null || fabricante.getCnpj().trim().equals(""))
        {
            throw new RegraNegocioException("CNPJ inválido");
        }
    }
}
