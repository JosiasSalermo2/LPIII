package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VacinaService {
    @Autowired
    private final VacinaRepository repository;




    public VacinaService(VacinaRepository repository) {
        this.repository = repository;
    }

    public List<Vacina> getVacina() {
        return repository.findAll();
    }

    public Optional<Vacina> getVacinaById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Vacina salvar(Vacina vacina){
        validar(vacina);
        return repository.save(vacina);
    }

    @Transactional
    public void excluir(Vacina vacina){
        Objects.requireNonNull(vacina.getId());

        try{
            repository.delete(vacina);
        } catch (DataIntegrityViolationException e){
            throw new  RegraNegocioException("Não é possível excluir a vacina. Existem registros vinculados.");
        }
    }

    private void validar(Vacina vacina)
    {
        if (vacina.getVacina() == null || vacina.getVacina().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da vacina é obrigatório.");
        }

        if (vacina.getIndicacao() == null || vacina.getIndicacao().trim().isEmpty()){
            throw new RegraNegocioException("A indicação da vacina é obrigatória.");
        }

        if (vacina.getContraIndicacao() == null || vacina.getContraIndicacao().trim().isEmpty()) {
            throw new RegraNegocioException("A contra indicação da vacina é obrigatória.");
        }

        if (vacina.getDosesAmpola() <= 0){
            throw new RegraNegocioException("A quantidade de doses por ampola deve ser maior que zero.");
        }

        if (vacina.getTipoVacina() == null || vacina.getTipoVacina().getId() == null){
            throw new RegraNegocioException("Tipo da vacina deve ser informado.");
        }

        if (vacina.getFabricante() == null || vacina.getFabricante().getId() == null) {
            throw new RegraNegocioException("Fabricante inválido");
        }

        if (vacina.getFornecedor() == null || vacina.getFornecedor().getId() == null) {
            throw new RegraNegocioException("O fornecedor da vacina deve ser informado.");
        }

        vacina.setVacina(vacina.getVacina().trim().toUpperCase());
    }
}
