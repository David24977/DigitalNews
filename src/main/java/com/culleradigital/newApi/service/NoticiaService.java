package com.culleradigital.newApi.service;

import com.culleradigital.newApi.dto.NoticiaRequestDto;
import com.culleradigital.newApi.dto.NoticiaResponseDto;
import com.culleradigital.newApi.dto.NoticiaUpdateRequest;
import com.culleradigital.newApi.model.Categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NoticiaService {

    Page<NoticiaResponseDto> listarNoticias(Pageable pageable);

    NoticiaResponseDto crearNoticia(NoticiaRequestDto noticiaRequestDto);

    NoticiaResponseDto modificarParcialNoticia(UUID id, NoticiaUpdateRequest noticiaUpdateRequest);

    NoticiaResponseDto eliminarNoticia(UUID id);

    NoticiaResponseDto buscarPorId(UUID id);

    List<NoticiaResponseDto> buscarPorFecha(LocalDate fecha);

    List<NoticiaResponseDto> buscarEntreFechas(LocalDate inicio, LocalDate fin);

    List<NoticiaResponseDto> buscarPorCategoria(Categoria categoria);

    List<NoticiaResponseDto> buscarPorTitular(String texto);

    List<NoticiaResponseDto> buscarPorDestacada();


}
