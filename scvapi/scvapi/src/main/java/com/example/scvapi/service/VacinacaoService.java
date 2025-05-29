package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.repository.VacinacaoRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
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
        if (vacinacao.getPaciente().getNome() == null || vacinacao.getPaciente().getNome().equals(""))
        {
            throw new RegraNegocioException("Paciente inválido");
        }
        if (vacinacao.getPaciente().getEmail() == null || vacinacao.getPaciente().getEmail().equals(""))
        {
            throw new RegraNegocioException("Email inválido");
        }
        if (vacinacao.getPaciente().getDataNascimento() == null || vacinacao.getPaciente().getDataNascimento().equals(""))
        {
            throw new RegraNegocioException("Data de nascimento inválida");
        }
        if (vacinacao == null || vacinacao.getDataAplicacao().equals(""))
        {
            throw new RegraNegocioException("Data de aplicação inválida");
        }
        if (vacinacao.getAgendamento() == null || vacinacao.getAgendamento().equals(""))
        {
            throw new RegraNegocioException("Agendamento inválido");
        }
    }
}
