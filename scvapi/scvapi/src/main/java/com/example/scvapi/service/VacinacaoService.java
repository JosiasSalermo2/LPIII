package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.repository.VacinacaoRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class VacinacaoService
{
    private VacinacaoRepository repository ;
    public VacinacaoService(VacinacaoRepository repository)
    {
        this.repository = repository;
    }

    public List<Vacinacao> getVacinacao()
    {
        return repository.findAll();
    }

    public Optional<Vacinacao> getVacinacaoById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Vacinacao salvar(Vacinacao vacinacao)
    {
        validar(vacinacao);
        return repository.save(vacinacao);
    }

    @Transactional
    public void excluir(Vacinacao vacinacao)
    {
        Objects.requireNonNull(vacinacao.getId());
        repository.delete(vacinacao);
    }

    private void validar(Vacinacao vacinacao)
    {
        if (vacinacao.getPaciente() == null || vacinacao.getPaciente().equals(""))
        {
            throw new RegraNegocioException("Paciente inválido");
        }
        if (vacinacao.getDataAplicacao() == null || vacinacao.getDataAplicacao().equals(""))
        {
            throw new RegraNegocioException("Data de aplicação inválida");
        }
        if (vacinacao.getAgendamento() == null || vacinacao.getAgendamento().equals(""))
        {
            throw new RegraNegocioException("Agendamento inválido");
        }
    }
}
