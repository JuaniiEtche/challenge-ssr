package com.eldar.challengessr.api.mappers;

import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.models.entities.Provincia;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper {

    ProvinciaMapper mapper = Mappers.getMapper(ProvinciaMapper.class);

    ProvinciaResponseDTO ProvinciatoProvinciaResponseDTO(Provincia provincia);
    Provincia ProvinciaRequestDTOtoProvincia(ProvinciaRequestDTO provinciaResponseDTO);

}
