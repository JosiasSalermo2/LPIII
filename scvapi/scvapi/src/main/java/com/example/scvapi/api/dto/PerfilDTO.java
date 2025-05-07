package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Agendamento;
import com.example.scvapi.model.entity.Perfil;
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

    public static PerfilDTO create(Perfil perfil) {
        ModelMapper modelMapper = new ModelMapper();
        PerfilDTO dto = modelMapper.map(perfil, PerfilDTO.class);
        dto.funcionarioId = perfil.getFuncionario().getId();
        return dto;
    }
}
