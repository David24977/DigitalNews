package com.culleradigital.newApi.dto;

import com.culleradigital.newApi.model.Categoria;

public record NoticiaUpdateRequest(
        String titular,
        String resumen,
        String contenido,
        Categoria categoria,
        String imagenUrl
) {
}
