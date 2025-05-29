package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TipoVacinaDTO;
import com.example.scvapi.model.entity.TipoVacina;
import com.example.scvapi.service.TipoVacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/tipo-vacinas")
@RequiredArgsConstructor
@CrossOrigin
public class TipoVacinaController
{
    private final TipoVacinaService tipoVacinaService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<TipoVacina> tipoVacina = tipoVacinaService.getTipoVacinaById(id);
        if (!tipoVacina.isPresent())
        {
            return new ResponseEntity("Tipo de vacina não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(tipoVacina.map(TipoVacinaDTO::create));
    }

    public TipoVacina converter(TipoVacinaDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        TipoVacina tipoVacina = modelMapper.map(dto, TipoVacina.class);
        return tipoVacina;
    }
}
