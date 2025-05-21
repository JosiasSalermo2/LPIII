package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.DescarteDTO;
import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.service.EstoqueService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class DescarteController
{
    private final EstoqueService estoqueService;

    public Descarte converter(DescarteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Descarte descarte = modelMapper.map(dto, Descarte.class);
        if (dto.getEstoqueId() != null)
        {
            Optional<Estoque> estoque = estoqueService.getEstoqueById(dto.getEstoqueId());
            if(!estoque.isPresent())
            {
                descarte.setEstoque(null);
            }
            else
            {
                descarte.setEstoque(estoque.get());
            }
        }
        return descarte;
    }
}
