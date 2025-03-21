package com.example.application.resource;

import java.net.URI;
import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.domains.contracts.services.PeliculasService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.domains.entities.dtos.FilmDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/peliculas/v1")
@Tag(name = "film-service", description = "Gestión de películas")
public class FilmResource {

    private final PeliculasService srv;

    public FilmResource(PeliculasService srv) {
        this.srv = srv;
    }

    @GetMapping(params = { "page" })
    @Operation(summary = "Obtener todas las películas con paginación")
    public List<FilmDTO> getAll(@Parameter(description = "Parámetros de paginación") Pageable pageable) {
        return srv.getByProjection(FilmDTO.class);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Obtener una película por ID")
    @ApiResponse(responseCode = "200", description = "Película encontrada exitosamente")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    public FilmDTO getOne(@PathVariable int id) throws NotFoundException {
        var item = srv.getOne(id);
        if (item.isEmpty()) {
            throw new NotFoundException("No se encontró la película con id " + id);
        }
        return FilmDTO.from(item.get());
    }

    @PostMapping
    @Operation(summary = "Crear una nueva película")
    @ApiResponse(responseCode = "201", description = "Película creada exitosamente")
    public ResponseEntity<Object> create(@Valid @RequestBody FilmDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
        var newItem = srv.add(FilmDTO.from(item));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newItem.getFilmId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una película existente")
    @ApiResponse(responseCode = "204", description = "Película actualizada exitosamente")
    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, el id no coincide")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @Valid @RequestBody FilmDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
        if (item.getFilmId() != id) {
            throw new BadRequestException("El id de la película no coincide con el recurso a modificar");
        }
        srv.modify(FilmDTO.from(item));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una película por ID")
    @ApiResponse(responseCode = "204", description = "Película eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        srv.deleteById(id);
    }

    @GetMapping(path = "/{id}/actores")
    @Operation(summary = "Obtener los actores de una película")
    @ApiResponse(responseCode = "200", description = "Actores encontrados exitosamente")
    @ApiResponse(responseCode = "404", description = "Película no encontrada")
    @Transactional
    public List<ActorDTO> getActors(@PathVariable int id) throws NotFoundException {
        var item = srv.getOne(id);
        if (item.isEmpty()) {
            throw new NotFoundException("No se encontró la película con id " + id);
        }
        return item.get().getFilmActors().stream()
                .map(o -> ActorDTO.from(o.getActor()))
                .toList();
    }
}
