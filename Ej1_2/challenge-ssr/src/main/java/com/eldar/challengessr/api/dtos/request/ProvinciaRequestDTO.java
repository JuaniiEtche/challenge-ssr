package com.eldar.challengessr.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ProvinciaRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El código no puede estar vacío")
    private String codigo31662;
}
