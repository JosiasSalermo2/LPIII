package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacinacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String dataAplicacao;

    @ManyToOne
    private Paciente paciente;

    @ManyToOne
    private Agendamento agendamento;

    @ManyToOne
    private Estoque estoque;
}
