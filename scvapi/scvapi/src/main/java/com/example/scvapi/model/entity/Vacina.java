package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vacina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vacina;
    private String indicacao;
    private String contraIndicacao;
    private int dosesAmpola;

    @ManyToOne
    private TipoVacina tipoVacinaDescricao;

    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToOne
    private Fabricante fabricante;
}
