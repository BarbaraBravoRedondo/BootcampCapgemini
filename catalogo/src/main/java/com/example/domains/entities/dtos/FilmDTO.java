package com.example.domains.entities.dtos;

import com.example.domains.entities.Film;
import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
public class FilmDTO {
    
    @Schema(name="id", description="ID único de la película", example = "1")
    @JsonProperty("id")
    private int filmId;
    
    @Schema(name="titulo", description="Título de la película", example = "The Matrix")
    @JsonProperty("titulo")
    private String title;

    @JsonProperty("descripcion")
    @Schema(name="descripcion", description="Descripción de la película", example = "Un programador informático descubre que el mundo en el que vive no es real.")
    private String description;

    @JsonProperty("ano_estreno")
    @Schema(name="Año de estreno", description="Año en el que la película fue estrenada", example = "1999")
    private Short releaseYear;

    @JsonProperty("duracion")
    @Schema(name="duracion", description="Duración de la película en minutos", example = "136")
    private int length;
    
    @Schema(name="calificacion", description="Calificación de la película (G, PG, PG-13, R, NC-17)", example = "R")
    @JsonProperty("calificacion")
    private String rating;

    @Schema(name="duracion_alquiler", description="Tiempo máximo de alquiler de la película en días", example = "7")
    @JsonProperty("duracion_alquiler")
    private byte rentalDuration;

    @Schema(name="precio_alquiler", description="Precio de alquiler de la película", example = "4.99")
    @JsonProperty("precio_alquiler")
    private BigDecimal rentalRate;
 
    @JsonProperty("costo_reemplazo")
    @Schema(name="costo_reemplazo", description="Costo de reemplazo de la película en caso de pérdida o daño", example = "19.99")
    private BigDecimal replacementCost;
    
    @Schema(name="idioma", description="Idioma principal de la película", example = "English")
    @JsonProperty("idioma")
    private Language language;

    @JsonProperty("idioma_original")
    @Schema(name="idioma_original", description="Idioma original de la película, si es diferente del idioma principal", example = "English")
    private Language languageVO;

    @JsonProperty("ultima_actualizacion")
    @Schema(name="ultima_actualizacion", description="Fecha y hora de la última actualización de la película", example = "2023-05-01T14:30:00")
    private Timestamp lastUpdate;

    // Método para convertir Film a FilmDTO
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
