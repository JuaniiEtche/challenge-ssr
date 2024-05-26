package com.eldar.challengessr.api.services.database.contracts;

import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;

import java.util.List;

public interface ProvinciaService {
    ProvinciaResponseDTO getById(Long id);

    List<ProvinciaResponseDTO> getAll();

    StatusResponseDTO create(ProvinciaRequestDTO request);

    StatusResponseDTO update(Long id,ProvinciaRequestDTO request);

    StatusResponseDTO delete(Long id);
}
