package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.ComorbidadesDTO;
import com.example.scvapi.model.entity.Comorbidades;
import com.example.scvapi.service.ComorbidadesService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/comorbidades")
@RequiredArgsConstructor
@CrossOrigin
public class ComorbidadesController {
    private final ComorbidadesService comorbidadesService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Comorbidades> comorbidades = comorbidadesService.getComorbidadesById(id);
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
