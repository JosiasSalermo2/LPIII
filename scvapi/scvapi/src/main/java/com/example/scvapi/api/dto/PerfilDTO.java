package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Usuario;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private Long id;
    private String perfil;
    private String acessos;
    private Long funcionarioId;

    public static PerfilDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        PerfilDTO dto = modelMapper.map(usuario, PerfilDTO.class);
        dto.funcionarioId = usuario.getFuncionario().getId();
        return dto;
    }
}
