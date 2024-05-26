package com.eldar.challengessr.api.services.cvs.impl;

import com.eldar.challengessr.api.models.entities.Localidad;
import com.eldar.challengessr.api.models.entities.Provincia;
import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.exceptions.customs.NotFoundException;
import com.eldar.challengessr.api.models.repositories.LocalidadRepository;
import com.eldar.challengessr.api.models.repositories.ProvinciaRepository;
import com.eldar.challengessr.api.services.cvs.contracts.CsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CsvServiceImpl implements CsvService {

    private final LocalidadRepository localidadRepository;
    private final ProvinciaRepository provinciaRepository;

    @Value("${csv.export.location.provincias}")
    private String provinciasCsvExportLocation;

    @Value("${csv.export.location.localidades}")
    private String localidadesCsvExportLocation;

    @Override
    public StatusResponseDTO exportCsv() {
        try (BufferedWriter provinciasWriter = new BufferedWriter(new FileWriter(provinciasCsvExportLocation));
             BufferedWriter localidadesWriter = new BufferedWriter(new FileWriter(localidadesCsvExportLocation))) {

            writeProvinciasHeader(provinciasWriter);
            writeProvinciasData(provinciasWriter);

            writeLocalidadesHeader(localidadesWriter);
            writeLocalidadesData(localidadesWriter);

            return StatusResponseDTO.builder().message("Exportación completada a: " + provinciasCsvExportLocation + " y " + localidadesCsvExportLocation).build();
        } catch (IOException e) {
            throw new RuntimeException("Error al exportar los datos a CSV", e);
        }
    }

    @Override
    public ProvinciaResponseDTO getProvinciaById(Long id) {
        List<ProvinciaResponseDTO> provincias = getAllProvincias();
        Optional<ProvinciaResponseDTO> optionalProvincia = provincias.stream().filter(p -> p.getId()==id).findFirst();
        return optionalProvincia.orElseThrow(() -> new NotFoundException("Provincia no encontrada"));
    }

    @Override
    public List<ProvinciaResponseDTO> getAllProvincias() {
        List<ProvinciaResponseDTO> provincias = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(provinciasCsvExportLocation))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6 || parts[5].isEmpty()) {
                    ProvinciaResponseDTO provincia = new ProvinciaResponseDTO();
                    provincia.setId(Long.parseLong(parts[0]));
                    provincia.setNombre(parts[1]);
                    provincia.setCodigo31662(parts[2]);
                    provincia.setCreatedAt(!(parts.length > 3) ? null : Timestamp.valueOf(parts[3]));
                    provincia.setUpdatedAt(!(parts.length > 4) ? null : Timestamp.valueOf(parts[4]));
                    provincia.setDeletedAt(!(parts.length > 5) ? null :Timestamp.valueOf(parts[5]));
                    provincias.add(provincia);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer provincias", e);
        }
        return provincias;
    }

    @Override
    public StatusResponseDTO addProvincia(ProvinciaRequestDTO request) {
        try {
            List<ProvinciaResponseDTO> provincias = getAllProvincias();
            Long maxId = provincias.stream()
                    .mapToLong(ProvinciaResponseDTO::getId)
                    .max()
                    .orElse(0L);

            Long newId = maxId + 1;
            Timestamp now = Timestamp.from(Instant.now());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(provinciasCsvExportLocation, true))) {
                writer.write(String.format("%d,%s,%s,%s,,", newId, request.getNombre(), request.getCodigo31662(), now));
                writer.newLine();
                return StatusResponseDTO.builder().message("Provincia agregada correctamente").build();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al agregar provincia", e);
        }
    }

    @Override
    public StatusResponseDTO updateProvincia(Long id, ProvinciaRequestDTO request) {
        List<ProvinciaResponseDTO> provincias = getAllProvincias();
        Optional<ProvinciaResponseDTO> optionalProvincia = provincias.stream().filter(p -> p.getId()==id).findFirst();
        if (optionalProvincia.isPresent()) {
            ProvinciaResponseDTO provincia = optionalProvincia.get();
            provincia.setNombre(request.getNombre());
            provincia.setCodigo31662(request.getCodigo31662());
            provincia.setUpdatedAt(Timestamp.from(Instant.now()));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(provinciasCsvExportLocation))) {
                writeProvinciasHeader(writer);
                for (ProvinciaResponseDTO p : provincias) {
                    writer.write(String.format("%d,%s,%s,%s,%s,%s",
                            p.getId(), p.getNombre(), p.getCodigo31662(),
                            p.getCreatedAt() != null ? p.getCreatedAt().toString() : "",
                            p.getUpdatedAt() != null ? p.getUpdatedAt().toString() : "",
                            p.getDeletedAt() != null ? p.getDeletedAt().toString() : ""));
                    writer.newLine();
                }
                return StatusResponseDTO.builder().message("Provincia actualizada correctamente").build();
            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar provincia", e);
            }
        } else {
            throw new NotFoundException("Provincia no encontrada");
        }
    }

    @Override
    public StatusResponseDTO deleteProvincia(Long id) {
        List<ProvinciaResponseDTO> provincias = getAllProvincias();
        Optional<ProvinciaResponseDTO> optionalProvincia = provincias.stream().filter(p -> p.getId()==id).findFirst();
        if (optionalProvincia.isPresent()) {
            ProvinciaResponseDTO provincia = optionalProvincia.get();
            provincia.setDeletedAt(Timestamp.from(Instant.now()));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(provinciasCsvExportLocation))) {
                writeProvinciasHeader(writer);
                for (ProvinciaResponseDTO p : provincias) {
                    writer.write(String.format("%d,%s,%s,%s,%s,%s",
                            p.getId(), p.getNombre(), p.getCodigo31662(),
                            p.getCreatedAt() != null ? p.getCreatedAt().toString() : "",
                            p.getUpdatedAt() != null ? p.getUpdatedAt().toString() : "",
                            p.getDeletedAt() != null ? p.getDeletedAt().toString() : ""));
                    writer.newLine();
                }
                return StatusResponseDTO.builder().message("Provincia eliminada correctamente").build();
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar provincia", e);
            }
        } else {
            throw new NotFoundException("Provincia no encontrada");
        }
    }

    private void writeProvinciasHeader(BufferedWriter writer) throws IOException {
        writer.write("ID,Nombre,Código31662,CreatedAt,UpdatedAt,DeletedAt");
        writer.newLine();
    }

    private void writeLocalidadesHeader(BufferedWriter writer) throws IOException {
        writer.write("ID,Nombre,Código Postal,Provincia ID,CreatedAt,UpdatedAt,DeletedAt");
        writer.newLine();
    }

    private void writeProvinciasData(BufferedWriter writer) throws IOException {
        List<Provincia> provincias = provinciaRepository.findAllProvincia();
        for (Provincia provincia : provincias) {
            writer.write(String.format("%d,%s,%s,%s,%s,%s",
                    provincia.getId(),
                    provincia.getNombre(),
                    provincia.getCodigo31662(),
                    provincia.getCreatedAt() != null ? provincia.getCreatedAt().toString() : "",
                    provincia.getUpdatedAt() != null ? provincia.getUpdatedAt().toString() : "",
                    provincia.getDeletedAt() != null ? provincia.getDeletedAt().toString() : ""));
            writer.newLine();
        }
    }

    private void writeLocalidadesData(BufferedWriter writer) throws IOException {
        List<Localidad> localidades = localidadRepository.findAllLocalidad();
        for (Localidad localidad : localidades) {
            writer.write(String.format("%d,%s,%s,%d,%s,%s,%s",
                    localidad.getId(),
                    localidad.getNombre(),
                    localidad.getCodigoPostal(),
                    localidad.getProvinciaId() != null ? localidad.getProvinciaId().getId() : 0,
                    localidad.getCreatedAt() != null ? localidad.getCreatedAt().toString() : "",
                    localidad.getUpdatedAt() != null ? localidad.getUpdatedAt().toString() : "",
                    localidad.getDeletedAt() != null ? localidad.getDeletedAt().toString() : ""));
            writer.newLine();
        }
    }

    public StatusResponseDTO addLocalidad(LocalidadRequestDTO request) {
        try {
            List<ProvinciaResponseDTO> provincias = getAllProvincias();
            boolean provinciaExists = provincias.stream()
                    .anyMatch(p -> p.getId()==request.getProvinciaId());

            if (!provinciaExists) {
                throw new NotFoundException("El provinciaId especificado no existe");
            }

            List<LocalidadResponseDTO> localidades = getAllLocalidades();
            Long maxId = localidades.stream()
                    .mapToLong(LocalidadResponseDTO::getId)
                    .max()
                    .orElse(0L);

            Long newId = maxId + 1;
            Timestamp now = Timestamp.from(Instant.now());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(localidadesCsvExportLocation, true))) {
                writer.write(String.format("%d,%s,%s,%d,%s,,", newId, request.getNombre(), request.getCodigoPostal(), request.getProvinciaId(), now));
                writer.newLine();
                return StatusResponseDTO.builder().message("Localidad agregada correctamente").build();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al agregar localidad", e);
        }
    }

    public StatusResponseDTO updateLocalidad(Long id, LocalidadRequestDTO request) {
        List<ProvinciaResponseDTO> provincias = getAllProvincias();
        boolean provinciaExists = provincias.stream()
                .anyMatch(p -> p.getId()==request.getProvinciaId());

        if (!provinciaExists) {
            throw new NotFoundException("El provinciaId especificado no existe");
        }

        List<LocalidadResponseDTO> localidades = getAllLocalidades();
        Optional<LocalidadResponseDTO> optionalLocalidad = localidades.stream().filter(p -> p.getId()==id).findFirst();
        if (optionalLocalidad.isPresent()) {
            LocalidadResponseDTO localidad = optionalLocalidad.get();
            localidad.setNombre(request.getNombre());
            localidad.setCodigoPostal(request.getCodigoPostal());
            localidad.setProvinciaId(request.getProvinciaId());
            localidad.setUpdatedAt(Timestamp.from(Instant.now()));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(localidadesCsvExportLocation))) {
                writeLocalidadesHeader(writer);
                for (LocalidadResponseDTO l : localidades) {
                    writer.write(String.format("%d,%s,%s,%d,%s,%s,%s",
                            l.getId(), l.getNombre(), l.getCodigoPostal(), l.getProvinciaId(),
                            l.getCreatedAt() != null ? l.getCreatedAt().toString() : "",
                            l.getUpdatedAt() != null ? l.getUpdatedAt().toString() : "",
                            l.getDeletedAt() != null ? l.getDeletedAt().toString() : ""));
                    writer.newLine();
                }
                return StatusResponseDTO.builder().message("Localidad actualizada correctamente").build();
            } catch (IOException e) {
                throw new RuntimeException("Error al actualizar localidad", e);
            }
        } else {
            throw new NotFoundException("Localidad no encontrada");
        }
    }

    public StatusResponseDTO deleteLocalidad(Long id) {
        List<LocalidadResponseDTO> localidades = getAllLocalidades();
        Optional<LocalidadResponseDTO> optionalLocalidad = localidades.stream().filter(p -> p.getId()==id).findFirst();
        if (optionalLocalidad.isPresent()) {
            LocalidadResponseDTO localidad = optionalLocalidad.get();
            localidad.setDeletedAt(Timestamp.from(Instant.now()));
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(localidadesCsvExportLocation))) {
                writeLocalidadesHeader(writer);
                for (LocalidadResponseDTO l : localidades) {
                    writer.write(String.format("%d,%s,%s,%d,%s,%s,%s",
                            l.getId(), l.getNombre(), l.getCodigoPostal(), l.getProvinciaId(),
                            l.getCreatedAt() != null ? l.getCreatedAt().toString() : "",
                            l.getUpdatedAt() != null ? l.getUpdatedAt().toString() : "",
                            l.getDeletedAt() != null ? l.getDeletedAt().toString() : ""));
                    writer.newLine();
                }
                return StatusResponseDTO.builder().message("Localidad eliminada correctamente").build();
            } catch (IOException e) {
                throw new RuntimeException("Error al eliminar localidad", e);
            }
        } else {
            throw new NotFoundException("Localidad no encontrada");
        }
    }

    public List<LocalidadResponseDTO> getAllLocalidades() {
        List<LocalidadResponseDTO> localidades = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(localidadesCsvExportLocation))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7 || parts[6].isEmpty()) {
                    LocalidadResponseDTO localidad = new LocalidadResponseDTO();
                    localidad.setId(Long.parseLong(parts[0]));
                    localidad.setNombre(parts[1]);
                    localidad.setCodigoPostal(parts[2]);
                    localidad.setProvinciaId(Long.parseLong(parts[3]));
                    localidad.setCreatedAt(!(parts.length > 4) ? null : Timestamp.valueOf(parts[4]));
                    localidad.setUpdatedAt(!(parts.length > 5) ? null : Timestamp.valueOf(parts[5]));
                    localidad.setDeletedAt(!(parts.length > 6) ? null : Timestamp.valueOf(parts[6]));
                    localidades.add(localidad);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer localidades", e);
        }
        return localidades;
    }

    public LocalidadResponseDTO getLocalidadById(Long id) {
        List<LocalidadResponseDTO> localidades = getAllLocalidades();
        Optional<LocalidadResponseDTO> optionalLocalidad = localidades.stream().filter(l -> l.getId() == id).findFirst();
        return optionalLocalidad.orElseThrow(() -> new NotFoundException("Localidad no encontrada"));
    }


}
