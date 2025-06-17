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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.scvapi.exception.RegraNegocioException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/compras")
@RequiredArgsConstructor
@CrossOrigin
public class CompraController
{
    private final CompraService compraService;
    private final FornecedorService fornecedorService;
    private final FabricanteService fabricanteService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Compra> compras = compraService.getCompra();
        return ResponseEntity.ok(compras.stream().map(CompraDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Compra> compra = compraService.getCompraById(id);
        if (!compra.isPresent())
        {
            return new ResponseEntity("Compra não encontrada.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(compra.map(CompraDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody CompraDTO dto) {
        try {
            Compra compra = converter(dto);
            compra = compraService.salvar(compra);
            return new ResponseEntity(compra, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CompraDTO dto) {
        if (!compraService.getCompraById(id).isPresent()) {
            return new ResponseEntity("Compra não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Compra compra = converter(dto);
            compra.setId(id);
            compraService.salvar(compra);
            return ResponseEntity.ok(compra);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
