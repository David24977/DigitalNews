package com.culleradigital.newApi.controller;


import com.culleradigital.newApi.dto.NoticiaRequestDto;
import com.culleradigital.newApi.dto.NoticiaResponseDto;
import com.culleradigital.newApi.dto.NoticiaUpdateRequest;
import com.culleradigital.newApi.model.Categoria;
import com.culleradigital.newApi.service.NoticiaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping
    public Page<NoticiaResponseDto> listarNoticias(
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable){
        return noticiaService.listarNoticias(pageable);
    }

    @PostMapping
    public NoticiaResponseDto crearNoticia(@Valid @RequestBody NoticiaRequestDto noticiaRequestDto){
        return noticiaService.crearNoticia(noticiaRequestDto);
    }

    @PatchMapping("/{id}")
    public NoticiaResponseDto modificarParcialNoticia(@PathVariable UUID id, @RequestBody NoticiaUpdateRequest noticiaUpdateRequest){
        return noticiaService.modificarParcialNoticia(id, noticiaUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public NoticiaResponseDto eliminarNoticia(@PathVariable UUID id){
        return noticiaService.eliminarNoticia(id);
    }

    @GetMapping(value = "/fecha", params = "fecha")
    public List<NoticiaResponseDto> buscarPorFecha(@RequestParam LocalDate fecha){
        return noticiaService.buscarPorFecha(fecha);
    }

    @GetMapping(value = "/entreFechas", params = {"inicio", "fin"})
    public List<NoticiaResponseDto> buscarEntreFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        return noticiaService.buscarEntreFechas(inicio, fin);
    }

    @GetMapping(value = "/categoria", params = "categoria")
    public List<NoticiaResponseDto> buscarPorCategoria(@RequestParam Categoria categoria){
        return noticiaService.buscarPorCategoria(categoria);
    }

    @GetMapping(value = "/titular", params = "texto")
    public List<NoticiaResponseDto> buscarPorTitular(@RequestParam String texto){
        return noticiaService.buscarPorTitular(texto);
    }


}
