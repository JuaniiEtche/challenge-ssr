package com.eldar.challengessr.api.controllers.database;

import com.eldar.challengessr.api.dtos.request.LocalidadRequestDTO;
import com.eldar.challengessr.api.dtos.response.LocalidadResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.services.database.contracts.LocalidadService;
import com.eldar.challengessr.utils.SwaggerResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("databaseLocalidadController")
@RequestMapping("/database/localidad")
@RequiredArgsConstructor
@Tag(name = "Localidad - Database")
public class LocalidadController extends SwaggerResponseCode {

    private final LocalidadService localidadService;

    @GetMapping("/get-by-id")
    @Operation(summary = "Get Localidad by ID")
    public ResponseEntity<LocalidadResponseDTO> getById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(localidadService.getById(id));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all Localidades")
    public ResponseEntity<List<LocalidadResponseDTO>> getAll() {
        return ResponseEntity.ok(localidadService.getAll());
    }

    @PostMapping
    @Operation(summary = "Create Localidad")
    public ResponseEntity<StatusResponseDTO> create(@Valid @RequestBody LocalidadRequestDTO request) {
        return ResponseEntity.ok(localidadService.create(request));
    }

    @PutMapping("/update")
    @Operation(summary = "Update Localidad")
    public ResponseEntity<StatusResponseDTO> update(
            @RequestParam("id") Long id,
            @Valid @RequestBody LocalidadRequestDTO request) {
        return ResponseEntity.ok(localidadService.update(id,request));
    }

    @DeleteMapping
    @Operation(summary = "Delete Localidad")
    public ResponseEntity<StatusResponseDTO> delete(@RequestParam Long id) {
        return ResponseEntity.ok(localidadService.delete(id));
    }
}
