package com.eldar.challengessr.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.sql.Timestamp;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProvinciaResponseDTO {
    private long id;
    private String nombre;
    private String codigo31662;
    @JsonIgnore
    private Timestamp createdAt;
    @JsonIgnore
    private Timestamp updatedAt;
    @JsonIgnore
    private Timestamp deletedAt;

}
