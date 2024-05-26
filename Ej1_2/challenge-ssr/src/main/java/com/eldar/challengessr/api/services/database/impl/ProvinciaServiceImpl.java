package com.eldar.challengessr.api.services.database.impl;

import com.eldar.challengessr.api.models.entities.Provincia;
import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.exceptions.customs.NotFoundException;
import com.eldar.challengessr.api.mappers.ProvinciaMapper;
import com.eldar.challengessr.api.models.repositories.ProvinciaRepository;
import com.eldar.challengessr.api.services.database.contracts.ProvinciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service("databaseProvinciaService")
@Transactional
public class ProvinciaServiceImpl implements ProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    public ProvinciaResponseDTO getById(Long id) {
      Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
        return ProvinciaMapper.mapper.ProvinciatoProvinciaResponseDTO(provincia);
    }

    public List<ProvinciaResponseDTO> getAll() {
        List<Provincia> provincias = provinciaRepository.findAll();
        return provincias.stream().map(
                ProvinciaMapper.mapper::ProvinciatoProvinciaResponseDTO).toList();
    }

    public StatusResponseDTO create(ProvinciaRequestDTO request) {
        Provincia provincia = ProvinciaMapper.mapper.ProvinciaRequestDTOtoProvincia(request);
        provinciaRepository.save(provincia);
        return StatusResponseDTO
                .builder()
                .message("Provincia creada correctamente")
                .build();
    }

    public StatusResponseDTO update(Long id,ProvinciaRequestDTO request) {
        provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
        Provincia provincia = ProvinciaMapper.mapper.ProvinciaRequestDTOtoProvincia(request);
        provincia.setId(id);
        provinciaRepository.save(provincia);
        return StatusResponseDTO
                .builder()
                .message("Provincia actualizada correctamente")
                .build();
    }

    public StatusResponseDTO delete(Long id) {
        Provincia provincia = provinciaRepository.findById(id).orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
        provinciaRepository.delete(provincia);
        return StatusResponseDTO
                .builder()
                .message("Provincia eliminada correctamente")
                .build();
    }
}