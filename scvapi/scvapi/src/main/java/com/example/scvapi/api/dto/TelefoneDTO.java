package com.example.scvapi.api.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {
    private Long id;
    private String ddd;
    private String numero;
}
