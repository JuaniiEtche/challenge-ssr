package com.eldar.challengessr.api.models.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.sql.Timestamp;

@Entity
@Table(name = "localidad")
@Getter
@Setter
@SQLDelete(sql = "UPDATE localidad SET deleted_at = CURRENT_TIMESTAMP WHERE id=?")
@SQLRestriction("deleted_at IS NULL")
public class Localidad {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Getter
        private String nombre;
        private String codigoPostal;

        @ManyToOne()
        @JoinColumn(name = "provincia_id")
        private Provincia provinciaId;

        @Column(updatable = false)
        @CreationTimestamp
        private Timestamp createdAt;

        @UpdateTimestamp
        private Timestamp updatedAt;

        private Timestamp deletedAt;

}
