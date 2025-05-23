package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteService
{
    private PacienteRepository repository;
    public PacienteService(PacienteRepository repository)
    {
        this.repository = repository;
    }

    public List<Paciente> getPaciente()
    {
        return repository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Paciente salvar(Paciente paciente)
    {
        validar(paciente);
        return repository.save(paciente);
    }

    @Transactional
    public void excluir(Paciente paciente)
    {
        Objects.requireNonNull(paciente.getId());
        repository.delete(paciente);
    }

    private void validar(Paciente paciente)
    {
        if (paciente.getNome() == null || paciente.getNome().trim().equals(""))
        {
            throw new RegraNegocioException("Nome inválido");
        }
        if (paciente.getEmail() == null || paciente.getEmail().trim().equals(""))
        {
            throw new RegraNegocioException("E-mail inválido");
        }
        if (paciente.getCpf() == null || paciente.getCpf().trim().equals(""))
        {
            throw new RegraNegocioException("CPF inválido");
        }
    }
}
