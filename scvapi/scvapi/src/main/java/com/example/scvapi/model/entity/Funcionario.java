package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario extends PessoaFisica{


    private String especialidade;
    private String registroConselho;
    private String dataAdmissao;
    private Double salario;

    @ManyToOne
    private Cargo cargo;

}
