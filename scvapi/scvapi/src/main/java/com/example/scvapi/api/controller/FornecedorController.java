package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FornecedorDTO;
import com.example.scvapi.model.entity.Fornecedor;
import com.example.scvapi.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedor")
@RequiredArgsConstructor
@CrossOrigin
public class FornecedorController
{
     private final FornecedorService service;

     public Fornecedor converter(FornecedorDTO dto)
     {
         ModelMapper modelMapper = new ModelMapper();
         Fornecedor fornecedor = modelMapper.map(dto, Fornecedor.class);
         return fornecedor;
     }

}
