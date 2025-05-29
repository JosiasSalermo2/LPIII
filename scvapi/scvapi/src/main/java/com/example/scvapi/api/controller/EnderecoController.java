package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.EnderecoDTO;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/endereco")
@RequiredArgsConstructor
@CrossOrigin
public class EnderecoController
{
    private final EnderecoService service;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Endereco> endereco = service.getEnderecoById(id);
        if (!endereco.isPresent())
        {
            return new ResponseEntity("Endereco não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(endereco.map(EnderecoDTO::create));
    }


    public Endereco converter(EnderecoDTO dto)
    {
        ModelMapper modelMapper = new ModelMapper();
        Endereco endereco = modelMapper.map(dto, Endereco.class);
        return endereco;
    }
}
