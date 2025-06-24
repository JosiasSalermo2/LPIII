package com.example.scvapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataValidade;
    private String numeroLote;
    private int numeroAmpola;
    private int dosesAmpola;

    @ManyToOne
    private Compra compra;

    @ManyToOne
    private Vacina vacina;

    @ManyToOne
    private Estoque estoque;
}
