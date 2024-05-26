package com.eldar.challengessr.api.controllers.database;

import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.services.database.contracts.ProvinciaService;
import com.eldar.challengessr.utils.SwaggerResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("databaseProvinciaController")
@RequestMapping("/database/provincia")
@RequiredArgsConstructor
@Tag(name = "Provincia - Database")
public class ProvinciaController extends SwaggerResponseCode {
    private final ProvinciaService provinciaService;

    @GetMapping("/get-by-id")
    @Operation(summary = "Get Provincia by ID")
    public ResponseEntity<ProvinciaResponseDTO> getById(@RequestParam Long id) {
        return ResponseEntity.ok(provinciaService.getById(id));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all Provincias")
    public ResponseEntity<List<ProvinciaResponseDTO>> getAll() {
        return ResponseEntity.ok(provinciaService.getAll());
    }

    @PostMapping
    @Operation(summary = "Create Provincia")
    public ResponseEntity<StatusResponseDTO> create(@Valid @RequestBody ProvinciaRequestDTO request) {
        return ResponseEntity.ok(provinciaService.create(request));
    }

    @PutMapping("/update")
    @Operation(summary = "Update Provincia")
    public ResponseEntity<StatusResponseDTO> update(
            @RequestParam Long id
            ,@Valid @RequestBody ProvinciaRequestDTO request) {
        return ResponseEntity.ok(provinciaService.update(id,request));
    }

    @DeleteMapping
    @Operation(summary = "Delete Provincia")
    public ResponseEntity<StatusResponseDTO> delete(@RequestParam Long id) {
        return ResponseEntity.ok(provinciaService.delete(id));
    }
}
