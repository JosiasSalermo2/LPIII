package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.VacinacaoDTO;
import com.example.scvapi.exception.RegraNegocioException;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vacinacoes")
@RequiredArgsConstructor
@CrossOrigin
public class VacinacaoController {
    private final VacinacaoService service;
    private final PacienteService pacienteService;
    private final AgendamentoService agendamentoService;

    @GetMapping()
    public ResponseEntity get(){
        List<Vacinacao> vacinacoes = service.getVacinacao();
        return ResponseEntity.ok(vacinacoes.stream().map(VacinacaoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Vacinacao> vacinacao = service.getVacinacaoById(id);
        if (!vacinacao.isPresent()) {
            return new ResponseEntity("Vacinacao não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(vacinacao.map(VacinacaoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody VacinacaoDTO dto) {
        try {
            Vacinacao vacinacao = converter(dto);
            vacinacao = service.salvar(vacinacao);
            return new ResponseEntity(vacinacao, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody VacinacaoDTO dto) {
        if (!service.getVacinacaoById(id).isPresent()) {
            return new ResponseEntity("Vacinacao não encontrada", HttpStatus.NOT_FOUND);
        }
        try {
            Vacinacao vacinacao = converter(dto);
            vacinacao.setId(id);
            service.salvar(vacinacao);
            return ResponseEntity.ok(vacinacao);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id) {
        Optional<Vacinacao> vacinacao = service.getVacinacaoById(id);
        if (!vacinacao.isPresent()) {
            return new ResponseEntity("Vacinacao não encontrada", HttpStatus.NOT_FOUND);
        }
        try{
            service.excluir(vacinacao.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
