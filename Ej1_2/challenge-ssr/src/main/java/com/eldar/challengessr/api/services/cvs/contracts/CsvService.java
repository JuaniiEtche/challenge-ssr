package com.eldar.challengessr.api.services.cvs.contracts;

import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;

import java.util.List;

import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;

public interface CsvService {
    StatusResponseDTO exportCsv();

    List<ProvinciaResponseDTO> getAllProvincias();

    ProvinciaResponseDTO getProvinciaById(Long id);

    StatusResponseDTO addProvincia(ProvinciaRequestDTO request);

    StatusResponseDTO updateProvincia(Long id, ProvinciaRequestDTO request);

    StatusResponseDTO deleteProvincia(Long id);

    List<LocalidadResponseDTO> getAllLocalidades();

    LocalidadResponseDTO getLocalidadById(Long id);

    StatusResponseDTO addLocalidad(LocalidadRequestDTO request);

    StatusResponseDTO updateLocalidad(Long id, LocalidadRequestDTO request);

    StatusResponseDTO deleteLocalidad(Long id);
}

