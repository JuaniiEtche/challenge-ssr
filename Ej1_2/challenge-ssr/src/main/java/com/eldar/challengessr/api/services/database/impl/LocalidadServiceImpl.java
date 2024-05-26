package com.eldar.challengessr.api.services.database.impl;

import com.eldar.challengessr.api.models.entities.Localidad;
import com.eldar.challengessr.api.models.entities.Provincia;
import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.exceptions.customs.NotFoundException;
import com.eldar.challengessr.api.mappers.LocalidadMapper;
import com.eldar.challengessr.api.models.repositories.LocalidadRepository;
import com.eldar.challengessr.api.models.repositories.ProvinciaRepository;
import com.eldar.challengessr.api.services.database.contracts.LocalidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service("databaseLocalidadService")
@Transactional
public class LocalidadServiceImpl implements LocalidadService {

    private final LocalidadRepository localidadRepository;
    private final ProvinciaRepository provinciaRepository;

    public LocalidadResponseDTO getById(Long id) {
        Localidad localidad = localidadRepository.findById(id).orElseThrow(() -> new NotFoundException("Localidad no encontrada"));
        return LocalidadMapper.mapper.localidadtoLocalidadResponseDTO(localidad);
    }

    public List<LocalidadResponseDTO> getAll() {
        List<Localidad> localidades = localidadRepository.findAll();
        return localidades.stream().map(
                LocalidadMapper.mapper::localidadtoLocalidadResponseDTO).toList();
    }

    public StatusResponseDTO create(LocalidadRequestDTO request) {
        Provincia provincia = provinciaRepository.findById(request.getProvinciaId()).orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
        Localidad localidad = LocalidadMapper.mapper.localidadRequestDTOtoLocalidad(request);
        localidad.setProvinciaId(provincia);
        localidadRepository.save(localidad);
        return StatusResponseDTO
                .builder()
                .message("Localidad creada correctamente")
                .build();
    }

    public StatusResponseDTO update(Long id,LocalidadRequestDTO request) {
        localidadRepository.findById(id).orElseThrow(() -> new RuntimeException("Localidad no encontrada"));
        Provincia provincia = provinciaRepository.findById(request.getProvinciaId()).orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
        Localidad localidad = LocalidadMapper.mapper.localidadRequestDTOtoLocalidad(request);
        localidad.setProvinciaId(provincia);
        localidad.setId(id);
        localidadRepository.save(localidad);
        return StatusResponseDTO
                .builder()
                .message("Localidad actualizada correctamente")
                .build();
    }

    public StatusResponseDTO delete(Long id) {
        Localidad localidad = localidadRepository.findById(id).orElseThrow(() -> new NotFoundException("Localidad no encontrada"));
        localidadRepository.delete(localidad);
        return StatusResponseDTO
                .builder()
                .message("Localidad eliminada correctamente")
                .build();
    }
}
