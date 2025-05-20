package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.VacinacaoDTO;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Paciente;
import com.example.scvapi.model.entity.Vacinacao;
import com.example.scvapi.service.AgendamentoService;
import com.example.scvapi.service.PacienteService;
import com.example.scvapi.service.VacinacaoService;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class VacinacaoController {
    private final VacinacaoService vacinacaoService;
    private final PacienteService pacienteService;
    private final AgendamentoService agendamentoService;

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
