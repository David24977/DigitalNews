package com.culleradigital.newApi.repository;

import com.culleradigital.newApi.model.Categoria;
import com.culleradigital.newApi.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface NoticiaRepository extends JpaRepository<Noticia, UUID> {

    List<Noticia> findByFechaOrderByCreatedAtDesc(LocalDate fecha);
    List<Noticia> findByFechaBetweenOrderByCreatedAtDesc(LocalDate inicio, LocalDate fin);
    List<Noticia> findByCategoria(Categoria categoria);
    List<Noticia> findByTitularContainingIgnoreCase(String texto);
    List<Noticia> findByDestacadaTrueOrderByCreatedAtDesc();
    @Modifying
    @Query("UPDATE Noticia n SET n.destacada = false WHERE n.destacada = true")
    void desmarcarTodasLasDestacadas();



}
