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
    private String contraindicacao;
    private String medicamentosRegulares;
    private Long telefoneId;
    private String telefoneDDD;
    private String telefoneNumero;
    private Long enderecoId;
    private String enderecoLogradouro;
    private String enderecoNumero;
    private String enderecoComplemento;
    private String enderecoBairro;
    private String enderecoCidade;
    private String enderecoUf;
    private String enderecoCep;


    public static PacienteDTO create(Paciente paciente) {
        ModelMapper modelMapper = new ModelMapper();
        PacienteDTO dto = modelMapper.map(paciente, PacienteDTO.class);
        dto.telefoneId = paciente.getTelefone().getId();
        dto.telefoneDDD = paciente.getTelefone().getDdd();
        dto.telefoneNumero = paciente.getTelefone().getNumero();
        dto.enderecoId = paciente.getEndereco().getId();
        dto.enderecoLogradouro = paciente.getEndereco().getLogradouro();
        dto.enderecoNumero = paciente.getEndereco().getNumero();
        dto.enderecoComplemento = paciente.getEndereco().getComplemento();
        dto.enderecoBairro = paciente.getEndereco().getBairro();
        dto.enderecoCidade = paciente.getEndereco().getCidade();
        dto.enderecoUf = paciente.getEndereco().getUf();
        dto.enderecoCep = paciente.getEndereco().getCep();
        return dto;
    }
}
