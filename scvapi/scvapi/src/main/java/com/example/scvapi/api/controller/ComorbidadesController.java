package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.ComorbidadesDTO;
import com.example.scvapi.model.entity.Comorbidades;
import com.example.scvapi.service.ComorbidadesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comorbidades")
@RequiredArgsConstructor
@CrossOrigin
public class ComorbidadesController {
    private final ComorbidadesService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Comorbidades> comorbidades = service.getComorbidades();
        return ResponseEntity.ok(comorbidades.stream().map(ComorbidadesDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Comorbidades> comorbidades = service.getComorbidadesById(id);
        if (!comorbidades.isPresent()) {
            return new ResponseEntity("Comorbidade não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(comorbidades.map(ComorbidadesDTO::create));
    }

    public Comorbidades converter(ComorbidadesDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Comorbidades comorbidades = modelMapper.map(dto, Comorbidades.class);
        return comorbidades;
    }

}
