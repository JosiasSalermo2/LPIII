package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.DescarteDTO;
import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/descarte")
@RequiredArgsConstructor
@CrossOrigin
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
