package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FornecedorDTO;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fornecedor")
@RequiredArgsConstructor
@CrossOrigin
public class FornecedorController
{
     private final FornecedorService fornecedorService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id)
    {
        Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(id);
        if (!fornecedor.isPresent())
        {
            return new ResponseEntity("Fornecedor não encontrado.", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(fornecedor.map(FornecedorDTO::create));
    }

     public Fornecedor converter(FornecedorDTO dto)
     {
         ModelMapper modelMapper = new ModelMapper();
         Fornecedor fornecedor = modelMapper.map(dto, Fornecedor.class);
         return fornecedor;
     }

}
