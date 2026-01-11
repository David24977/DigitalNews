package com.culleradigital.newApi.dto;

import com.culleradigital.newApi.model.Categoria;

import java.time.LocalDate;
import java.util.UUID;

public record NoticiaResponseDto(
        UUID id,
        String titular,
        String resumen,
        String contenido,
        LocalDate fecha,
        Categoria categoria,
        String imagenUrl,
        Boolean destacada
) {
}
