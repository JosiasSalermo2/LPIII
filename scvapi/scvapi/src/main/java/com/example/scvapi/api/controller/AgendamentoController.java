package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.AgendamentoDTO;
import com.example.scvapi.exception.RegraNegocioException;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.model.entity.Vacina;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.service.AgendamentoService;
import com.example.scvapi.service.PacienteService;
import com.example.scvapi.service.VacinaService;
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
@RequestMapping("/api/v1/agendamentos")
@RequiredArgsConstructor
@CrossOrigin
public class AgendamentoController {
    private final AgendamentoService service;
    private final PacienteService pacienteService;
    private final VacinacaoService vacinacaoService;
    private final VacinaService vacinaService;

    @GetMapping()
    public ResponseEntity get(){
        List<Agendamento> agendamentos = service.getAgendamento();
        return ResponseEntity.ok(agendamentos.stream().map(AgendamentoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable ("id") Long id)
    {
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return ResponseEntity.status(404).body("Agendamento não encontrado");
        }
        return ResponseEntity.ok(agendamento.map(AgendamentoDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody AgendamentoDTO dto){
        try{
            Agendamento agendamento = converter(dto);
            agendamento = service.salvar(agendamento);
            return new ResponseEntity(agendamento, HttpStatus.CREATED);
        } catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AgendamentoDTO dto){
        if (!service.getAgendamentoById(id).isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Agendamento agendamento = converter(dto);
            agendamento.setId(id);
            service.salvar(agendamento);
            return ResponseEntity.ok(agendamento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable("id") Long id){
        Optional<Agendamento> agendamento = service.getAgendamentoById(id);
        if (!agendamento.isPresent()) {
            return new ResponseEntity("Agendamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try{
            service.excluir(agendamento.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (RegraNegocioException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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
        if (dto.getVacinaId() != null) {
            Optional<Vacina> vacina = vacinaService.getVacinaById(dto.getVacinaId());
            if(!vacina.isPresent()) {
                agendamento.setVacina(null);
            }else{
                agendamento.setVacina(vacina.get());
            }
        }
        return agendamento;
    }
}
