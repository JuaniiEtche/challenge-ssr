package com.eldar.challengessr.api.services.database.contracts;

import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;

import java.util.List;

public interface LocalidadService {

    LocalidadResponseDTO getById(Long id);

    List<LocalidadResponseDTO> getAll();

    StatusResponseDTO create(LocalidadRequestDTO request);

    StatusResponseDTO update(Long id,LocalidadRequestDTO request);

    StatusResponseDTO delete(Long id);
}
