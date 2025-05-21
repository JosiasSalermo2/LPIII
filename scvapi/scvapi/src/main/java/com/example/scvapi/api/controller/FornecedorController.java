package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.FornecedorDTO;
import com.example.scvapi.model.entity.Fornecedor;
import org.modelmapper.ModelMapper;

public class FornecedorController
{
     private final FornecedorController service;

     public Fornecedor converter(FornecedorDTO dto)
     {
         ModelMapper modelMapper = new ModelMapper();
         Fornecedor fornecedor = modelMapper.map(dto, Fornecedor.class);
         return fornecedor;
     }

}
