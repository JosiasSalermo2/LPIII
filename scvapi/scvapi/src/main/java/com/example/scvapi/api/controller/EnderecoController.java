package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.DescarteDTO;
import com.example.scvapi.api.dto.EnderecoDTO;
import com.example.scvapi.model.entity.Descarte;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
@CrossOrigin
public class EnderecoController
{
    private final EnderecoService enderecoService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Endereco> enderecos = enderecoService.getEndereco();
        return ResponseEntity.ok(enderecos.stream().map(EnderecoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Endereco> endereco = enderecoService.getEnderecoById(id);
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
