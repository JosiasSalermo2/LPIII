package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.AgendamentoDTO;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.service.AgendamentoService;
import com.example.scvapi.service.PacienteService;
import com.example.scvapi.service.VacinacaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@CrossOrigin
public class AgendamentoController {
    private final AgendamentoService agendamentoService;
    private final PacienteService pacienteService;
    private final VacinacaoService vacinacaoService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable ("id") Long id)
    {
        Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(404).body("Agendamento não encontrado");
        }
        return ResponseEntity.ok(agendamento.map(AgendamentoDTO::create));
    }

    public Agendamento converter(AgendamentoDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Agendamento agendamento = modelMapper.map(dto, Agendamento.class);
        if (dto.getPacienteId() != null) {
            Optional<Paciente> paciente = pacienteService.getPacienteById(dto.getPacienteId());
            if(!paciente.isPresent()) {
                agendamento.setPaciente(null);
            }else{
                agendamento.setPaciente(paciente.get());
            }
        }
        return agendamento;
    }
}
