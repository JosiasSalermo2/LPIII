package com.example.scvapi.api.controller;

import com.example.scvapi.api.dto.AgendamentoDTO;
import com.example.scvapi.model.entity.Agendamento;
import org.modelmapper.ModelMapper;

public class AgendamentoController {

    public Agendamento converter(AgendamentoDTO dto){
        ModelMapper modelMapper = new ModelMapper();
        Agendamento agendamento = modelMapper.map(dto, Agendamento.class);
        return agendamento;
    }
}
