package com.example.scvapi.api.dto;
import com.example.scvapi.model.entity.Endereco;
import com.example.scvapi.model.entity.Telefone;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {
    private Long id;
    private String ddd;
    private String numero;

    public static TelefoneDTO create(Telefone telefone) {
        ModelMapper modelMapper = new ModelMapper();
        TelefoneDTO dto = modelMapper.map(telefone, TelefoneDTO.class);
        return dto;
    }
}
