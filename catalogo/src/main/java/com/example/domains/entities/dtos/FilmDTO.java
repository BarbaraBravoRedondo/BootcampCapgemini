package com.example.domains.entities.dtos;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class FilmDTO {
    @JsonProperty("id")
    private int filmId;

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("descripcion")
    private String description;

    @JsonProperty("ano_estreno")
    private Short releaseYear;

    @JsonProperty("duracion")
    private int length;

    @JsonProperty("calificacion")
    private String rating;

    @JsonProperty("duracion_alquiler")
    private byte rentalDuration;

    @JsonProperty("precio_alquiler")
    private BigDecimal rentalRate;

    @JsonProperty("costo_reemplazo")
    private BigDecimal replacementCost;

    @JsonProperty("idioma")
    private Language language;

    @JsonProperty("idioma_original")
    private Language languageVO;

    @JsonProperty("ultima_actualizacion")
    private Timestamp lastUpdate;

    public static FilmDTO from(Film source) {
        return new FilmDTO(
            source.getFilmId(),
            source.getTitle(),
            source.getDescription(),
            source.getReleaseYear(),
            source.getLength(),
            source.getRating(),
            source.getRentalDuration(),
            source.getRentalRate(),
            source.getReplacementCost(),
            source.getLanguage(),
            source.getLanguageVO(),
            source.getLastUpdate()
        );
    }

    public static Film from(FilmDTO source) {
        return new Film(
            source.getFilmId(),
            source.getTitle(),
            source.getDescription()
        );
    }
}
