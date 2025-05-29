package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.PacienteDTO;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/paciente")
@RequiredArgsConstructor
@CrossOrigin
public class PacienteController {
    private final PacienteService pacienteService;

    public Paciente converter(PacienteDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Paciente paciente = modelMapper.map(dto, Paciente.class);
        return paciente;
    }
}
