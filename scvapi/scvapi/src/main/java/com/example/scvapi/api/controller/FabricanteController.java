package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FabricanteDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Fabricante;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.service.FabricanteService;
import com.example.scvapi.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fabricantes")
@RequiredArgsConstructor
@CrossOrigin
public class FabricanteController
{
    private final FabricanteService fabricanteService;
    private final TelefoneService telefoneService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Fabricante> fabricantes = fabricanteService.getFabricante();
        return ResponseEntity.ok(fabricantes.stream().map(FabricanteDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(id);
        if (!fabricante.isPresent())
        {
            return new ResponseEntity("Fabricante não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(fabricante.map(FabricanteDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody FabricanteDTO dto) {
        try {
            Fabricante fabricante = converter(dto);
            fabricante = fabricanteService.salvar(fabricante);
            return new ResponseEntity(fabricante, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FabricanteDTO dto) {
        if (!fabricanteService.getFabricanteById(id).isPresent()) {
            return new ResponseEntity("Fabricante não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Fabricante fabricante = converter(dto);
            fabricante.setId(id);
            fabricanteService.salvar(fabricante);
            return ResponseEntity.ok(fabricante);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(id);
        if (!fabricante.isPresent()) {
            return new ResponseEntity("Fabricante não encontrado.", HttpStatus.NOT_FOUND);
        }
        try {
            fabricanteService.excluir(fabricante.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Fabricante converter(FabricanteDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Fabricante fabricante = modelMapper.map(dto, Fabricante.class);
        if (dto.getTelefoneId() != null)
        {
            Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getTelefoneId());
            if(!telefone.isPresent())
            {
                fabricante.setTelefone(null);
            }else {
                fabricante.setTelefone(telefone.get());
            }
        }
        return fabricante;
    }
}
