package com.eldar.challengessr.api.models.repositories;

import com.eldar.challengessr.api.models.entities.Localidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocalidadRepository extends JpaRepository<Localidad, Long> {
    @Query(value = "SELECT * FROM localidad",nativeQuery = true)
    List<Localidad> findAllLocalidad();
}