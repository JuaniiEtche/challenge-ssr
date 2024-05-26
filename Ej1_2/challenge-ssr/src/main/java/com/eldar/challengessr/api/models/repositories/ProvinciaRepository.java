package com.eldar.challengessr.api.models.repositories;

import com.eldar.challengessr.api.models.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {
    @Query(value = "SELECT * FROM provincia",nativeQuery = true)
    List<Provincia> findAllProvincia();
}