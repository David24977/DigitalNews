package com.culleradigital.newApi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 300)
    private String titular;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String resumen;

    @Lob
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String contenido;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "VARCHAR(50)")
    private Categoria categoria;

    @Column(columnDefinition = "TEXT")
    private String imagenUrl;

    @Column(nullable = false)
    private Boolean destacada = false;

    @PrePersist
    private void prePersist(){

        this.fecha = LocalDate.now();
        this.createdAt = LocalDateTime.now();
    }

}
