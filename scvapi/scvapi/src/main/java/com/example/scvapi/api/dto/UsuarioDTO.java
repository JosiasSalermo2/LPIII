package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Usuario;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;

    @NotBlank(message = "Login obrigatório")
    private String login;

    @NotBlank(message = "CPF obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "Campo administrador obrigatório")
    private boolean administrador;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        return dto;
    }
}
