package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Comorbidades;
import com.example.scvapi.model.entity.Paciente;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private String dataNascimento;
    private String tipoSanguineo;
    private String alergia;
    private String contraIndicacao;
    private String medicamentosRegulares;


    public static PacienteDTO create(Paciente paciente) {
        ModelMapper modelMapper = new ModelMapper();
        PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
        return dto;
    }
}
