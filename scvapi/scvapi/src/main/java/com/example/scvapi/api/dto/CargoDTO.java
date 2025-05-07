package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Cargo;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CargoDTO {
    private Long id;
    private String cargo;
    private String descricao;
    private Long funcionarioId;
    private String funcionarioNome;

    public static CargoDTO create(Cargo cargo) {
        ModelMapper modelMapper = new ModelMapper();
        CargoDTO dto = modelMapper.map(cargo, CargoDTO.class);
        dto.funcionarioNome = cargo.getFuncionario().getNome();
        return dto;
    }
}
