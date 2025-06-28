package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.repository.AgendamentoRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AgendamentoService 
{
    private AgendamentoRepository repository;
    public AgendamentoService(AgendamentoRepository repository)
    {
        this.repository = repository;
    }

    public List<Agendamento> getAgendamento()
    {
        return repository.findAll();
    }

    public Optional<Agendamento> getAgendamentoById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Agendamento salvar(Agendamento agendamento)
    {
        validar(agendamento);
        return repository.save(agendamento);
    }

    @Transactional
    public void excluir(Agendamento agendamento)
    {
        Objects.requireNonNull(agendamento.getId());
        repository.delete(agendamento);
    }

    private void validar(Agendamento agendamento)
    {
        if (agendamento.getDataAgendamento() == null || agendamento.getDataAgendamento().trim().equals("")) {
            throw new RegraNegocioException("Data inválida");
        }
        if (agendamento.getHorarioAgendamento() == null || agendamento.getHorarioAgendamento().trim().equals("")) {
            throw new RegraNegocioException("Horário inválido");
        }
        if (agendamento.getPaciente() == null || agendamento.getPaciente().getId() == null) {
            throw new RegraNegocioException("Paciente inválido");
        }

    }
}
