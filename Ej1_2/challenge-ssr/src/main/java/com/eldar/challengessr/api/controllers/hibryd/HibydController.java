package com.eldar.challengessr.api.controllers.hibryd;

import com.eldar.challengessr.api.services.hibryd.contracts.HibrydService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/hybrid")
@RequiredArgsConstructor
@Tag(name = "Hybrid")
public class HibydController {

    private final HibrydService hibrydService;

    @GetMapping("/localidad")
    @Operation(summary = "Get localidad by id")
    public ResponseEntity<Map<String,Object>> getLocalidadById(@RequestParam Long id) throws InterruptedException {
        return ResponseEntity.ok(hibrydService.getLocalidadById(id));
    }

    @GetMapping("/provincia")
    @Operation(summary = "Get provincia by id")
    public ResponseEntity<Map<String,Object>> getProvinciaById(@RequestParam Long id) throws InterruptedException {
        return ResponseEntity.ok(hibrydService.getProvinciaById(id));
    }
}
