package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TelefoneDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Telefone;
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
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor
@CrossOrigin
public class TelefoneController {

    private final TelefoneService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Telefone> telefones = service.getTelefone();
        return ResponseEntity.ok(telefones.stream().map(TelefoneDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Telefone> telefone = service.getTelefoneById(id);
        if (!telefone.isPresent()) {
            return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(telefone.map(TelefoneDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody TelefoneDTO dto) {
        try {
            Telefone telefone = converter(dto);
            telefone = service.salvar(telefone);
            return new ResponseEntity(telefone, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Telefone converter(TelefoneDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Telefone telefone = modelMapper.map(dto, Telefone.class);
        return telefone;
    }
}
