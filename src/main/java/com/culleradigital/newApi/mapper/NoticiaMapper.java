package com.culleradigital.newApi.mapper;

import com.culleradigital.newApi.dto.NoticiaRequestDto;
import com.culleradigital.newApi.dto.NoticiaResponseDto;
import com.culleradigital.newApi.model.Noticia;

import java.util.UUID;

public class NoticiaMapper {

    private NoticiaMapper(){} //Evita que se instancie

    //DTO->ENTITY

    public static Noticia toEntity(NoticiaRequestDto noticiaRequestDto){

        Noticia noticia = new Noticia();

        noticia.setTitular(noticiaRequestDto.titular());
        noticia.setResumen(noticiaRequestDto.resumen());
        noticia.setContenido(noticiaRequestDto.contenido());
        noticia.setCategoria(noticiaRequestDto.categoria());
        noticia.setImagenUrl(noticiaRequestDto.imagenUrl());
        return noticia;

    }


    // Entity → DTO
    public static NoticiaResponseDto toResponseDto(Noticia noticia) {
        return new NoticiaResponseDto(
                noticia.getId(),
                noticia.getTitular(),
                noticia.getResumen(),
                noticia.getContenido(),
                noticia.getFecha(),
                noticia.getCategoria(),
                noticia.getImagenUrl()
        );
    }
}
