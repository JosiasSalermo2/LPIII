package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.VacinacaoDTO;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.service.AgendamentoService;
import com.example.scvapi.service.PacienteService;
import com.example.scvapi.service.VacinacaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vacinacoes")
@RequiredArgsConstructor
@CrossOrigin
public class VacinacaoController {
    private final VacinacaoService vacinacaoService;
    private final PacienteService pacienteService;
    private final AgendamentoService agendamentoService;

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Vacinacao> vacinacao = vacinacaoService.getVacinacaoById(id);
        if (!vacinacao.isPresent()) {
            return new ResponseEntity("Vacinacao não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vacinacao.map(VacinacaoDTO::create));
    }

    public Vacinacao converter(VacinacaoDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Vacinacao vacinacao = modelMapper.map(dto, Vacinacao.class);
        if (dto.getPacienteId() != null) {
            Optional<Paciente> paciente = pacienteService.getPacienteById(dto.getPacienteId());
            if(!paciente.isPresent()) {
                vacinacao.setPaciente(null);
            }else{
                vacinacao.setPaciente(paciente.get());
            }
        }
        if(dto.getAgendamentoId() != null) {
            Optional<Agendamento> agendamento = agendamentoService.getAgendamentoById(dto.getAgendamentoId());
            if(!agendamento.isPresent()) {
                vacinacao.setAgendamento(null);
            }else{
                vacinacao.setAgendamento(agendamento.get());
            }
        }
        return vacinacao;
    }
}
