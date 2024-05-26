package com.eldar.challengessr.api.controllers.csv;

import com.eldar.challengessr.api.dtos.response.StatusResponseDTO;
import com.eldar.challengessr.api.services.cvs.contracts.CsvService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export-csv")
@RequiredArgsConstructor
@Tag(name = "Export CSV")
public class CsvController {

    private final CsvService csvService;

    @PostMapping("/export")
    @Operation(description = "Esta operación exportará los datos de la" +
            " base de datos y creará dos archivos csv con dichos datos en" +
            " la ubicación definida", summary = "Export CSV")
    public ResponseEntity<StatusResponseDTO> exportCsv() {
        return ResponseEntity.ok(csvService.exportCsv());
    }
}
