package com.culleradigital.newApi.service.serviceImpl;

import com.culleradigital.newApi.dto.NoticiaRequestDto;
import com.culleradigital.newApi.dto.NoticiaResponseDto;
import com.culleradigital.newApi.dto.NoticiaUpdateRequest;
import com.culleradigital.newApi.mapper.NoticiaMapper;
import com.culleradigital.newApi.model.Categoria;
import com.culleradigital.newApi.model.Noticia;
import com.culleradigital.newApi.repository.NoticiaRepository;
import com.culleradigital.newApi.service.NoticiaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class NoticiaServiceImpl implements NoticiaService {
    private final NoticiaRepository noticiaRepository;

    public NoticiaServiceImpl(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @Override
    public Page<NoticiaResponseDto> listarNoticias(Pageable pageable) {
        Page<Noticia> page = noticiaRepository.findAll(pageable);
        return page.map(NoticiaMapper::toResponseDto);
    }

    @Override
    public NoticiaResponseDto crearNoticia(NoticiaRequestDto noticiaRequestDto) {
        Noticia noticia = NoticiaMapper.toEntity(noticiaRequestDto);

        Noticia guardada = noticiaRepository.save(noticia);

        return NoticiaMapper.toResponseDto(guardada);
    }

    @Override
    public NoticiaResponseDto modificarParcialNoticia(UUID id, NoticiaUpdateRequest noticiaUpdateRequest) {
       Noticia noticia = noticiaRepository.findById(id)
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Noticia no encontrada"));
       if(noticiaUpdateRequest.titular() != null){
           noticia.setTitular(noticiaUpdateRequest.titular());
       }

       if(noticiaUpdateRequest.resumen() != null){
           noticia.setResumen(noticiaUpdateRequest.resumen());
       }

       if(noticiaUpdateRequest.contenido() != null){
           noticia.setContenido(noticiaUpdateRequest.contenido());
       }
       if(noticiaUpdateRequest.categoria() != null){
           noticia.setCategoria(noticiaUpdateRequest.categoria());
       }
       if(noticiaUpdateRequest.imagenUrl() != null){
           noticia.setImagenUrl(noticiaUpdateRequest.imagenUrl());
       }
       Noticia update = noticiaRepository.save(noticia);

       return NoticiaMapper.toResponseDto(update);
    }

    @Override
    public NoticiaResponseDto eliminarNoticia(UUID id) {
        Noticia noticia = noticiaRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Noticia no encontrada"));

        noticiaRepository.delete(noticia);

        return NoticiaMapper.toResponseDto(noticia);
    }

    @Override
    public List<NoticiaResponseDto> buscarPorFecha(LocalDate fecha) {
        return noticiaRepository.findByFechaOrderByCreatedAtDesc(fecha)
                .stream()
                .map(NoticiaMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<NoticiaResponseDto> buscarEntreFechas(LocalDate inicio, LocalDate fin) {
        return noticiaRepository.findByFechaBetweenOrderByCreatedAtDesc(inicio, fin)
                .stream()
                .map(NoticiaMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<NoticiaResponseDto> buscarPorCategoria(Categoria categoria) {
        return noticiaRepository.findByCategoria(categoria)
                .stream()
                .map(NoticiaMapper::toResponseDto)
                .toList();
    }

    @Override
    public List<NoticiaResponseDto> buscarPorTitular(String texto) {
        return noticiaRepository.findByTitularContainingIgnoreCase(texto)
                .stream()
                .map(NoticiaMapper::toResponseDto)
                .toList();
    }
}
