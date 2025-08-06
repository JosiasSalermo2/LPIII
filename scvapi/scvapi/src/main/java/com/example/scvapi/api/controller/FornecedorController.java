package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FornecedorDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.model.entity.Telefone;
import com.example.scvapi.service.FornecedorService;
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
@RequestMapping("/api/v1/fornecedores")
@RequiredArgsConstructor
@CrossOrigin
public class FornecedorController
{
     private final FornecedorService fornecedorService;
     private final TelefoneService telefoneService;

    @GetMapping()
    public ResponseEntity get()
    {
        List<Fornecedor> fornecedores = fornecedorService.getFornecedor();
        return ResponseEntity.ok(fornecedores.stream().map(FornecedorDTO::create).collect(Collectors.toList()));
    }

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

    @PostMapping()
    public ResponseEntity post(@RequestBody FornecedorDTO dto) {
        try {
            Fornecedor fornecedor = converter(dto);
            fornecedor = fornecedorService.salvar(fornecedor);
            return new ResponseEntity(fornecedor, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FornecedorDTO dto) {
        if (!fornecedorService.getFornecedorById(id).isPresent()) {
            return new ResponseEntity("Fornecedor não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Fornecedor fornecedor = converter(dto);
            fornecedor.setId(id);
            fornecedorService.salvar(fornecedor);
            return ResponseEntity.ok(fornecedor);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorService.getFornecedorById(id);
        if (!fornecedor.isPresent()) {
            return new ResponseEntity("Fornecedor não encontrado.", HttpStatus.NOT_FOUND);
        }
        try {
            fornecedorService.excluir(fornecedor.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

     public Fornecedor converter(FornecedorDTO dto)
     {
         ModelMapper modelMapper = new ModelMapper();
         Fornecedor fornecedor = modelMapper.map(dto, Fornecedor.class);
         if (dto.getTelefoneId() != null)
         {
             Optional<Telefone> telefone = telefoneService.getTelefoneById(dto.getTelefoneId());
             if(!telefone.isPresent())
             {
                 fornecedor.setTelefone(null);
             }else {
                 fornecedor.setTelefone(telefone.get());
             }
         }
         return fornecedor;
     }

}
