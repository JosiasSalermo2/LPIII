package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Cargo;
import com.example.scvapi.repository.CargoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CargoService 
{
    private CargoRepository repository;
    public CargoService(CargoRepository repository)
    {
        this.repository = repository;
    }

    public List<Cargo> getCargo()
    {
        return repository.findAll();
    }

    public Optional<Cargo> getCargoById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Cargo salvar(Cargo cargo)
    {
        validar(cargo);
        return repository.save(cargo);
    }

    @Transactional
    public void excluir(Cargo cargo)
    {
        Objects.requireNonNull(cargo.getId());
        repository.delete(cargo);
    }

    private void validar(Cargo cargo)
    {
        if (cargo.getCargo() == null || cargo.getCargo().trim().equals("")) {
            throw new RegraNegocioException("Cargo inválido");
        }
    }
}
