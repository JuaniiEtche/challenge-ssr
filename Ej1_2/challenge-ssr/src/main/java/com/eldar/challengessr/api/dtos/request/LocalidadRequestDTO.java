package com.eldar.challengessr.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LocalidadRequestDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;
    @NotBlank(message = "El código postal no puede estar vacío")
    private String codigoPostal;
    @NotNull(message = "La provincia no puede estar vacía")
    private Long provinciaId;
}
