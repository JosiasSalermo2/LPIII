package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private String dataCompra;

    @ManyToOne
    private Fornecedor fornecedor;

    @ManyToOne
    private Fabricante fabricante;
}
