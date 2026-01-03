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

    @Column(nullable = false)
    private String titular;
    @Lob
    @Column(nullable = false)
    private String resumen;
    @Lob
    @Column(nullable = false)
    private String contenido;
    @Column(nullable = false)
    private LocalDate fecha;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Categoria categoria;
    private String imagenUrl;

    @PrePersist
    private void prePersist(){

        this.fecha = LocalDate.now();
        this.createdAt = LocalDateTime.now();
    }

}
