package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.repository.DescarteRepository;
import com.example.scvapi.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DescarteService
{
    private final DescarteRepository repository;
    private final EstoqueRepository estoqueRepository;

    public DescarteService(DescarteRepository descarteRepository, EstoqueRepository estoqueRepository) {
        this.repository = descarteRepository;
        this.estoqueRepository = estoqueRepository;
    }


    public List<Descarte> getDescarte()
    {
        return repository.findAll();
    }

    public Optional<Descarte> getDescarteById(Long id)
    {
        return repository.findById(id);
    }

    @Transactional
    public Descarte salvar(Descarte descarte) {
        validar(descarte);

        Estoque estoque = estoqueRepository.findById(descarte.getEstoque().getId())
                .orElseThrow(() -> new RegraNegocioException("Estoque não encontrado"));

        int quantidadeDescarte = descarte.getQuantidadeDescarte();

        if (estoque.getQuantidadeDisponivel() < quantidadeDescarte) {
            throw new RegraNegocioException("Quantidade em estoque insuficiente para o descarte.");
        }

        estoque.setQuantidadeDisponivel(estoque.getQuantidadeDisponivel() - quantidadeDescarte);
        estoqueRepository.save(estoque);

        descarte.setEstoque(estoque);
        return repository.save(descarte);
    }

    @Transactional
    public void excluir(Descarte descarte)
    {
        Objects.requireNonNull(descarte.getId());
        repository.delete(descarte);
    }

    private void validar(Descarte descarte)
    {
        if (descarte.getQuantidadeDescarte() == 0 || descarte.getQuantidadeDescarte() < 0) {
            throw new RegraNegocioException("Quantidade inválida");
        }
        if (descarte.getEstoque().getNome() == null || descarte.getEstoque().getNome().equals("")) {
            throw new RegraNegocioException("Estoque inválido");
        }
    }
}
