package com.example.domains.entities.dtos;

import com.example.domains.entities.Language;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "Language", description = "Datos del idioma")
public class LanguageDTO {
    
    @JsonProperty("id")
    private int languageId;

    @NotBlank
    @Size(min = 1, max = 20)
    @Schema(description = "Nombre del idioma", example = "Español", required = true, minLength = 1, maxLength = 20)
    private String name;

   
    public static LanguageDTO from(Language source) {
        return new LanguageDTO(source.getLanguageId(), source.getName());
    }


    public static Language from(LanguageDTO source) {
        return new Language(source.getLanguageId(), source.getName());
    }
}
