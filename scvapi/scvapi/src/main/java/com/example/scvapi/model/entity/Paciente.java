package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente extends PessoaFisica {



    private String tipoSanguineo;
    private String alergia;
    private String contraindicacao;
    private String medicamentosRegulares;

    @ManyToOne
    private Telefone telefone;

    @ManyToOne
    private Endereco endereco;

}
