package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TelefoneDTO;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.service.TelefoneService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/telefones")
@RequiredArgsConstructor
@CrossOrigin
public class TelefoneController {

    private final TelefoneService telefoneService;
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Telefone> telefone = telefoneService.getTelefoneById(id);
        if (!telefone.isPresent()) {
            return new ResponseEntity("Telefone não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(telefone.map(TelefoneDTO::create));
    }

    public Telefone converter(TelefoneDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Telefone telefone = modelMapper.map(dto, Telefone.class);
        return telefone;
    }
}
