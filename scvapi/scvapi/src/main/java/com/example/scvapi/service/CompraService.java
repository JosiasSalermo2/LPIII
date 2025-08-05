package com.example.scvapi.service;

import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.repository.CompraRepository;
import com.example.scvapi.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CompraService
{
    private final CompraRepository repository;
    private final EstoqueRepository estoqueRepository;

    public CompraService(CompraRepository compraRepository, EstoqueRepository estoqueRepository) {
        this.repository = compraRepository;
        this.estoqueRepository = estoqueRepository;
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
    public Compra salvar(Compra compra) {
        validar(compra);

        Estoque estoque = estoqueRepository.findById(compra.getEstoque().getId())
                .orElseThrow(() -> new RegraNegocioException("Estoque não encontrado."));

        int novaQuantidade = estoque.getQuantidadeDisponivel() + compra.getQuantidadeVacina();

        if (novaQuantidade > estoque.getQuantidadeMaxima()) {
            throw new RegraNegocioException("Quantidade excede o limite máximo de armazenamento.");
        }

        estoque.setQuantidadeDisponivel(novaQuantidade);
        estoqueRepository.save(estoque);

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
        if (compra.getFabricante().getNomeFantasia() == null || compra.getFabricante().getNomeFantasia().equals("")) {
            throw new RegraNegocioException("Fabricante inválido");
        }
        if (compra.getFabricante().getCnpj() == null || compra.getFabricante().getCnpj().equals("")) {
            throw new RegraNegocioException("CNPJ inválido");
        }
        if (compra.getFabricante().getRazaoSocial() == null || compra.getFabricante().getRazaoSocial().equals("")) {
            throw new RegraNegocioException("Razão social inválido");
        }
        if (compra.getFabricante().getEmail() == null || compra.getFabricante().getEmail().equals("")) {
            throw new RegraNegocioException("E-mail inválido");
        }
        if (compra.getValor() == 0 || compra.getValor() < 0) {
            throw new RegraNegocioException("Valor inválido");
        }
        if (compra.getDataCompra() == null || compra.getDataCompra().equals("")) {
            throw new RegraNegocioException("Data inválida");
        }
        if (compra.getVacina() == null || compra.getVacina().equals("")) {
            throw new RegraNegocioException("Vacina inválida");
        }
        if (compra.getQuantidadeVacina() == 0 || compra.getQuantidadeVacina() < 0) {
            throw new RegraNegocioException("Quantidade de vacina inválida.");
        }
        if (compra.getQuantidadeVacina() <= 0) {
            throw new RegraNegocioException("Quantidade da compra deve ser maior que zero.");
        }



        //Falta validar Telefone e Endereço (Essas classes não tem relação no código com Compra) e, por isso, os objetos não aparecem
        //As classes Vacina e TipoVacina também não estão relacionadas com Compra, e os objetos também não aparecem.




    }
}
