package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.repository.CompraRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CompraService
{
    private CompraRepository repository;
    public CompraService(CompraRepository compraRepository)
    {
        this.repository = compraRepository;
    }

    public List<Compra> getCompra()
    {
        return repository.findAll();
    }

    public Optional<Compra> getCompraById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Compra salvar(Compra compra)
    {
        validar(compra);
        return repository.save(compra);
    }

    @Transactional
    public void excluir(Compra compra)
    {
        Objects.requireNonNull(compra.getId());
        repository.delete(compra);
    }

    private void validar(Compra compra)
    {
        if (tipoVacina.getTipoVacina() == null || tipoVacina.getTipoVacina().trim().equals("")) {
            throw new RegraNegocioException("Tipo de vacina inválido");
        }
    }
}
