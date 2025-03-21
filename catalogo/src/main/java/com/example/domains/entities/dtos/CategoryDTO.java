package com.example.domains.entities.dtos;

import com.example.domains.entities.Category;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "Categorias", description = "tipos de categorias")
public class CategoryDTO {
    @JsonProperty("id")
    @Schema(description = "Id", example = "2", required=true)
    private int categoryId;

    @JsonProperty("nombre")
	@Schema(description = "Nombre", example = "Drama", minLength = 2, maxLength = 25)
    private String name;

    public static CategoryDTO from(Category source) {
        return new CategoryDTO(
            source.getCategoryId(),
            source.getName()
        );
    }

    public static Category from(CategoryDTO source) {
        return new Category(
            source.getCategoryId(),
            source.getName()
        );
    }
}
