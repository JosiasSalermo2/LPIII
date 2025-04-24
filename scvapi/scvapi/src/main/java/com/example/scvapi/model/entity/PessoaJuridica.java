package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PessoaJuridica extends Pessoa{


    private String nomeFantasia;
    private String email;
    private String cnpj;
    private String razaoSocial;
}
