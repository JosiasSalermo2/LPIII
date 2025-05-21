package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.VacinaDTO;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.model.entity.TipoVacina;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.service.FabricanteService;
import com.example.scvapi.service.FornecedorService;
import com.example.scvapi.service.TipoVacinaService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class VacinaController
{
    private final TipoVacinaService tipoVacinaService;
    private final FornecedorService fornecedorService;
    private final FabricanteService fabricanteService;

    public Vacina converter(VacinaDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Vacina vacina = modelMapper.map(dto, Vacina.class);
        if(dto.getTipoVacinaDescricaoId() != null)
        {
            Optional<TipoVacina> tipoVacina = tipoVacinaService.getTipoVacinaById(dto.getTipoVacinaDescricaoId());
            if(!tipoVacina.isPresent())
            {
                vacina.setTipoVacinaDescricao(null);
            }
            else
            {
                vacina.setTipoVacinaDescricao(tipoVacina.get());
            }
        }
        if (dto.getFornecedorId() != null)
        {
            Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(dto.getFornecedorId());
            if(!fornecedor.isPresent())
            {
                vacina.setFornecedor(null);
            }
            else
            {
                vacina.setFornecedor(fornecedor.get());
            }
        }
        if (dto.getFabricanteId() != null)
        {
            Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(dto.getFabricanteId());
            if(!fabricante.isPresent())
            {
                vacina.setFabricante(null);
            }
            else
            {
                vacina.setFabricante(fabricante.get());
            }
        }
        return vacina;
    }
}
