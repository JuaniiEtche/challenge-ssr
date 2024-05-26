package com.eldar.challengessr.api.controllers.csv;

import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.services.cvs.contracts.CsvService;
import com.eldar.challengessr.utils.SwaggerResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("csvLocalidadController")
@RequestMapping("/csv/localidad")
@RequiredArgsConstructor
@Tag(name = "Localidad - CSV")
public class LocalidadController extends SwaggerResponseCode {

    private final CsvService csvService;

    @GetMapping("/get-by-id")
    @Operation(summary = "Get Localidad by ID")
    public ResponseEntity<LocalidadResponseDTO> getById(@Valid @RequestParam Long id) {
        return ResponseEntity.ok(csvService.getLocalidadById(id));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all Localidades")
    public ResponseEntity<List<LocalidadResponseDTO>> getAll() {
        return ResponseEntity.ok(csvService.getAllLocalidades());
    }

    @PostMapping
    @Operation(summary = "Create Localidad")
    public ResponseEntity<StatusResponseDTO> create(@Valid @RequestBody LocalidadRequestDTO request) {
        return ResponseEntity.ok(csvService.addLocalidad(request));
    }

    @PutMapping("/update")
    @Operation(summary = "Update Localidad")
    public ResponseEntity<StatusResponseDTO> update(
            @RequestParam Long id,
            @Valid @RequestBody LocalidadRequestDTO request) {
        return ResponseEntity.ok(csvService.updateLocalidad(id,request));
    }

    @DeleteMapping
    @Operation(summary = "Delete Localidad")
    public ResponseEntity<StatusResponseDTO> delete(@RequestParam Long id) {
        return ResponseEntity.ok(csvService.deleteLocalidad(id));
    }
}
