package com.eldar.challengessr.api.controllers.csv;

import com.eldar.challengessr.api.dtos.request.ProvinciaRequestDTO;
import com.eldar.challengessr.api.dtos.response.ProvinciaResponseDTO;
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

@RestController("csvProvinciaController")
@RequestMapping("/csv/provincia")
@RequiredArgsConstructor
@Tag(name = "Provincia - CSV")
public class ProvinciaController extends SwaggerResponseCode {

    private final CsvService csvService;

    @GetMapping("/get-by-id")
    @Operation(summary = "Get Provincia by ID")
    public ResponseEntity<ProvinciaResponseDTO> getById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(csvService.getProvinciaById(id));
    }

    @GetMapping("/get-all")
    @Operation(summary = "Get all Provincias")
    public ResponseEntity<List<ProvinciaResponseDTO>> getAll() {
        return ResponseEntity.ok(csvService.getAllProvincias());
    }

    @PostMapping
    @Operation(summary = "Create Provincia")
    public ResponseEntity<StatusResponseDTO> create(@Valid @RequestBody ProvinciaRequestDTO request) {
        return ResponseEntity.ok(csvService.addProvincia(request));
    }

    @PutMapping("/update")
    @Operation(summary = "Update Provincia")
    public ResponseEntity<StatusResponseDTO> update(
            @RequestParam("id") Long id
            ,@Valid @RequestBody ProvinciaRequestDTO request) {
        return ResponseEntity.ok(csvService.updateProvincia(id,request));
    }

    @DeleteMapping
    @Operation(summary = "Delete Provincia")
    public ResponseEntity<StatusResponseDTO> delete(@RequestParam("id") Long id) {
        return ResponseEntity.ok(csvService.deleteProvincia(id));
    }
}
