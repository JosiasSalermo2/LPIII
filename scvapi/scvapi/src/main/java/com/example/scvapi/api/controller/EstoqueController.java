package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EstoqueDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Estoque;
import com.example.scvapi.service.EstoqueService;
import com.example.scvapi.service.FabricanteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/estoques")
@RequiredArgsConstructor
@CrossOrigin
public class EstoqueController
{
    private final EstoqueService estoqueService;
    private final FabricanteService fabricanteService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Estoque> estoques = estoqueService.getEstoque();
        return ResponseEntity.ok(estoques.stream().map(EstoqueDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Estoque> estoque = estoqueService.getEstoqueById(id);
        if(!estoque.isPresent())
        {
            return new ResponseEntity("Estoque não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estoque.map(EstoqueDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody EstoqueDTO dto) {
        try {
            Estoque estoque = converter(dto);
            estoque = estoqueService.salvar(estoque);
            return new ResponseEntity(EstoqueDTO.create(estoque), HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody EstoqueDTO dto) {
        if (!estoqueService.getEstoqueById(id).isPresent()) {
            return new ResponseEntity("Estoque não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Estoque estoque = converter(dto);
            estoque.setId(id);
            estoqueService.salvar(estoque);
            return ResponseEntity.ok(EstoqueDTO.create(estoque));
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Estoque> estoque = estoqueService.getEstoqueById(id);
        if (!estoque.isPresent()) {
            return new ResponseEntity("Estoque não encontrado.", HttpStatus.NOT_FOUND);
        }
        try {
            estoqueService.excluir(estoque.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Estoque converter(EstoqueDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Estoque estoque = modelMapper.map(dto, Estoque.class);

        if(dto.getFabricanteId() != null){
            fabricanteService.getFabricanteById(dto.getFabricanteId()).ifPresent(estoque::setFabricante);
        }
        return estoque;
    }
}