package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.CompraDTO;
import com.example.scvapi.model.entity.Compra;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.service.CompraService;
import com.example.scvapi.service.FabricanteService;
import com.example.scvapi.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/compra")
@RequiredArgsConstructor
@CrossOrigin
public class CompraController
{
    private final CompraService service;
    private final FornecedorService fornecedorService;
    private final FabricanteService fabricanteService;

    public Compra converter(CompraDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Compra compra = modelMapper.map(dto, Compra.class);
        if (dto.getFornecedorId() != null)
        {
            Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(dto.getFornecedorId());
            if (!fornecedor .isPresent())
            {
                compra.setFornecedor(null);
            }
            else
            {
                compra.setFornecedor(fornecedor.get());
            }
        }
        if (dto.getFabricanteId() != null)
        {
            Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(dto.getFabricanteId());
            if (!fabricante.isPresent())
            {
                compra.setFabricante(null);
            }
            else
            {
                compra.setFabricante(fabricante.get());
            }
        }
        return compra;
    }
}
