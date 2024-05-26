package com.eldar.challengessr.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LocalidadResponseDTO {

    private long id;
    private String nombre;
    private String codigoPostal;
    private long provinciaId;
    @JsonIgnore
    private Timestamp createdAt;
    @JsonIgnore
    private Timestamp updatedAt;
    @JsonIgnore
    private Timestamp deletedAt;
}
