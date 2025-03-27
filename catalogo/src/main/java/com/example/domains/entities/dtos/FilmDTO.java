package com.example.domains.entities.dtos;

import com.example.domains.entities.Film;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.AccessMode;

import java.math.BigDecimal;
import java.util.List;

@Value
public class FilmDTO {

    @Schema(description = "Id de la película", accessMode = AccessMode.READ_ONLY)
    private int filmId;

    @Schema(description = "Descripción de la película")
    private String description;

    @Schema(description = "La duración de la película, en minutos")
    private Integer length;

    @Schema(description = "La clasificación por edades asignada a la película", allowableValues = {"G", "PG", "PG-13", "R", "NC-17"})
    private String rating;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    @Schema(description = "El año en que se estrenó la película")
    private Short releaseYear;

    @Schema(description = "La duración del tiempo de alquiler, en días" , type = "integer" )
    private Byte rentalDuration;

    @Schema(description = "El coste de alquiler")
    private BigDecimal rentalRate;

    @Schema(description = "El importe cobrado al cliente si la película no se devuelve o se devuelve en un estado dañado")
    private BigDecimal replacementCost;

    @Schema(description = "El título ")
    private String title;

    @Schema(description = "El idioma ")
    private String language;

    @Schema(description = "El idioma original ")
    private String languageVO;

    @Schema(description = "La lista de actores que participan en la película")
    private List<String> actors;

    @Schema(description = "La lista de categorías asignadas a la película")
    private List<String> categories;

    // Método para mapear desde la entidad Film a FilmDTO
    public static FilmDTO from(Film source) {
        return new FilmDTO(
                source.getFilmId(),
                source.getDescription(),
                source.getLength(),
                source.getRating() == null ? null : source.getRating().getValue(),
                source.getReleaseYear(),
                source.getRentalDuration(),
                source.getRentalRate(),
                source.getReplacementCost(),
                source.getTitle(),
                source.getLanguage() == null ? null : source.getLanguage().getName(),
                source.getLanguageVO() == null ? null : source.getLanguageVO().getName(),
                source.getActors().stream().map(item -> item.getFirstName() + " " + item.getLastName()).sorted().toList(),
                source.getCategories().stream().map(item -> item.getName()).sorted().toList()
        );
    }
}
