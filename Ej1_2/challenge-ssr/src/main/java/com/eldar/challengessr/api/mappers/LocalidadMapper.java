package com.eldar.challengessr.api.mappers;

import com.eldar.challengessr.api.models.entities.Provincia;
import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.models.entities.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface LocalidadMapper {

    LocalidadMapper mapper = Mappers.getMapper(LocalidadMapper.class);

    @Mappings({
            @Mapping(target = "provinciaId", source = "provinciaId.id")
    })
    LocalidadResponseDTO localidadtoLocalidadResponseDTO(Localidad localidad);

    @Mappings({
            @Mapping(target = "provinciaId", source = "provinciaId")
    })
    Localidad localidadRequestDTOtoLocalidad(LocalidadRequestDTO localidadResponseDTO);

    default Provincia mapIdToProvincia(long provinciaId) {
        Provincia provincia = new Provincia();
        provincia.setId(provinciaId);
        return provincia;
    }

}
