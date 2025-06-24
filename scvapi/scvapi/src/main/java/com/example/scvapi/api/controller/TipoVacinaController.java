package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.TipoVacinaDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.TipoVacina;
import com.example.scvapi.service.TipoVacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tipos-vacinas")
@RequiredArgsConstructor
@CrossOrigin
public class TipoVacinaController
{
    private final TipoVacinaService tipoVacinaService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<TipoVacina> tipoVacinas = tipoVacinaService.getTipoVacina();
        return ResponseEntity.ok(tipoVacinas.stream().map(TipoVacinaDTO::create).collect(Collectors.toList()));
    }

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

    @PostMapping()
    public ResponseEntity post(@RequestBody TipoVacinaDTO dto) {
        try {
            TipoVacina tipoVacina = converter(dto);
            tipoVacina = tipoVacinaService.salvar(tipoVacina);
            return new ResponseEntity(tipoVacina, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody TipoVacinaDTO dto) {
        if (!tipoVacinaService.getTipoVacinaById(id).isPresent()) {
            return new ResponseEntity("Tipo de vacina não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            TipoVacina tipoVacina = converter(dto);
            tipoVacina.setId(id);
            tipoVacinaService.salvar(tipoVacina);
            return ResponseEntity.ok(tipoVacina);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<TipoVacina> tipoVacina = tipoVacinaService.getTipoVacinaById(id);
        if (!tipoVacina.isPresent()) {
            return new ResponseEntity("Tipo de vacina não encontrado.", HttpStatus.NOT_FOUND);
        }
        try {
            tipoVacinaService.excluir(tipoVacina.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public TipoVacina converter(TipoVacinaDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        TipoVacina tipoVacina = modelMapper.map(dto, TipoVacina.class);
        return tipoVacina;
    }
}
