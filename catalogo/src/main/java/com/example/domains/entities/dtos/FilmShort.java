package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface FilmShort {
    @Value("#{target.filmId}")
    int getId();

    @Value("#{target.title}")
    String getTitulo();

    @Value("#{target.releaseYear}")
    Short getAnoEstreno();

    @Value("#{target.rating}")
    String getCalificacion();
}
