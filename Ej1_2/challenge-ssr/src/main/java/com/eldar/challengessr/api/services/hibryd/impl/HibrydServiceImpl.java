package com.eldar.challengessr.api.services.hibryd.impl;

import com.eldar.challengessr.api.services.cvs.contracts.CsvService;
import com.eldar.challengessr.api.services.database.contracts.LocalidadService;
import com.eldar.challengessr.api.services.database.contracts.ProvinciaService;
import com.eldar.challengessr.api.services.hibryd.contracts.HibrydService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class HibrydServiceImpl implements HibrydService {

    private final LocalidadService localidadService;
    private final ProvinciaService provinciaService;
    private final CsvService csvService;
    public Map<String, Object> getLocalidadById(Long id) throws InterruptedException {
        CompletableFuture<Map<String, Object>> response1 = getLocalidadDatabase(id);
        CompletableFuture<Map<String, Object>> response2 = getLocalidadCsv(id);

        Map<String, Object> result = new HashMap<>();

        Instant start1 = Instant.now();
        Map<String, Object> dbResult = response1.join();
        Instant end1 = Instant.now();
        long timeElapsed1 = Duration.between(start1, end1).toNanos();
        dbResult.put("timeElapsed", timeElapsed1);

        Instant start2 = Instant.now();
        Map<String, Object> csvResult = response2.join();
        Instant end2 = Instant.now();
        long timeElapsed2 = Duration.between(start2, end2).toNanos();
        csvResult.put("timeElapsed", timeElapsed2);

        result.put("database", dbResult);
        result.put("csv", csvResult);

        return result;
    }

    public Map<String, Object> getProvinciaById(Long id) throws InterruptedException {
        CompletableFuture<Map<String, Object>> response1 = getProvinciaDatabase(id);
        CompletableFuture<Map<String, Object>> response2 = getProvinciaCsv(id);

        Map<String, Object> result = new HashMap<>();

        Instant start1 = Instant.now();
        Map<String, Object> dbResult = response1.join();
        Instant end1 = Instant.now();
        long timeElapsed1 = Duration.between(start1, end1).toNanos();
        dbResult.put("timeElapsed", timeElapsed1);

        Instant start2 = Instant.now();
        Map<String, Object> csvResult = response2.join();
        Instant end2 = Instant.now();
        long timeElapsed2 = Duration.between(start2, end2).toNanos();
        csvResult.put("timeElapsed", timeElapsed2);

        result.put("database", dbResult);
        result.put("csv", csvResult);

        return result;
    }


    @Async("getExecutor")
    public CompletableFuture<Map<String,Object>> getLocalidadDatabase(long id) throws InterruptedException {
        Map<String,Object> response = new HashMap<>();
        response.put("localidad",localidadService.getById(id));
        return CompletableFuture.completedFuture(response);
    }

    @Async("getExecutor")
    public CompletableFuture<Map<String,Object>> getLocalidadCsv(long id) throws InterruptedException {
        Map<String,Object> response = new HashMap<>();
        response.put("localidad",csvService.getLocalidadById(id));
        return CompletableFuture.completedFuture(response);
    }

    @Async("getExecutor")
    public CompletableFuture<Map<String,Object>> getProvinciaDatabase(long id) throws InterruptedException {
        Map<String,Object> response = new HashMap<>();
        response.put("provincia",provinciaService.getById(id));
        return CompletableFuture.completedFuture(response);
    }

    @Async("getExecutor")
    public CompletableFuture<Map<String,Object>> getProvinciaCsv(long id) throws InterruptedException {
        Map<String,Object> response = new HashMap<>();
        response.put("provincia",csvService.getProvinciaById(id));
        return CompletableFuture.completedFuture(response);
    }


}
