package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private String tipoPaciente;
    private String alergia;
    private String contraindicacao;
    private String medicamento;


}
