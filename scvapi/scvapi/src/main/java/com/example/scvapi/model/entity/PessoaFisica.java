package com.example.scvapi.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PessoaFisica extends Pessoa{


    private String dataNascimento;
    private String cpf;
    private String rg;

}
