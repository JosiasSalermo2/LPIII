package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.VacinaDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.*;
import com.example.scvapi.service.FabricanteService;
import com.example.scvapi.service.FornecedorService;
import com.example.scvapi.service.TipoVacinaService;
import com.example.scvapi.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vacinas")
@RequiredArgsConstructor
@CrossOrigin
public class VacinaController
{
    private final TipoVacinaService tipoVacinaService;
    private final FornecedorService fornecedorService;
    private final FabricanteService fabricanteService;
    private final VacinaService vacinaService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Vacina> vacinas = vacinaService.getVacina();
        return ResponseEntity.ok(vacinas.stream().map(VacinaDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Vacina> vacina = vacinaService.getVacinaById(id);
        if (!vacina.isPresent())
        {
            return new ResponseEntity("Vacina não encontrada.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vacina.map(VacinaDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody VacinaDTO dto) {
        try {
            Vacina vacina = converter(dto);
            vacina = vacinaService.salvar(vacina);
            return new ResponseEntity(vacina, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody VacinaDTO dto) {
        if (!vacinaService.getVacinaById(id).isPresent()) {
            return new ResponseEntity("Vacina não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Vacina vacina = converter(dto);
            vacina.setId(id);
            vacinaService.salvar(vacina);
            return ResponseEntity.ok(vacina);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Vacina> vacina = vacinaService.getVacinaById(id);
        if (!vacina.isPresent()) {
            return new ResponseEntity("Vacina não encontrada.", HttpStatus.NOT_FOUND);
        }
        try {
            vacinaService.excluir(vacina.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Vacina converter(VacinaDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Vacina vacina = modelMapper.map(dto, Vacina.class);
        if(dto.getTipoVacinaId() != null)
        {
            Optional<TipoVacina> tipoVacina = tipoVacinaService.getTipoVacinaById(dto.getTipoVacinaId());
            if(!tipoVacina.isPresent())
            {
                vacina.setTipoVacinaDescricao(null);
            }
            else
            {
                vacina.setTipoVacinaDescricao(tipoVacina.get());
            }
        }
        if (dto.getFornecedorId() != null)
        {
            Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(dto.getFornecedorId());
            if(!fornecedor.isPresent())
            {
                vacina.setFornecedor(null);
            }
            else
            {
                vacina.setFornecedor(fornecedor.get());
            }
        }
        if (dto.getFabricanteId() != null)
        {
            Optional<Fabricante> fabricante = fabricanteService.getFabricanteById(dto.getFabricanteId());
            if(!fabricante.isPresent())
            {
                vacina.setFabricante(null);
            }
            else
            {
                vacina.setFabricante(fabricante.get());
            }
        }
        return vacina;
    }
}
