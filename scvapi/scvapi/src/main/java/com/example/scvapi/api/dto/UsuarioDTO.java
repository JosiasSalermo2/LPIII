package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Usuario;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String login;
    private String cpf;
    private boolean administrador;
    private Long funcionarioId;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        dto.funcionarioId = usuario.getFuncionario().getId();
        return dto;
    }
}
