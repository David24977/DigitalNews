package com.culleradigital.newApi.dto;

import com.culleradigital.newApi.model.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NoticiaRequestDto(
        @NotBlank
        String titular,
        @NotBlank
        String resumen,
        @NotBlank
        String contenido,
        @NotNull
        Categoria categoria,
        String imagenUrl
) {
}
