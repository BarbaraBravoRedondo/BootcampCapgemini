package com.example.application.resource;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
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

import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.dtos.ActorDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import org.springdoc.core.annotations.ParameterObject;

import org.springframework.data.web.PagedModel;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/actores/v1")

@Tag(name = "actor-service", description = "Gestión de actores")
public class ActoresResource {
	private ActoresService srv;

	public ActoresResource(ActoresService srv) {
		super();
		this.srv = srv;
	}
	@Hidden
//	@GetMapping
//	public List<ActorDTO> getAll(Pageable pageable) {
//		return srv.getByProjection(ActorDTO.class);
//	}
	@GetMapping(params = { "page" })
 	@Operation(summary = " actores paginados")
 	public List<ActorDTO> getAll(@ParameterObject Pageable pageable) {
 		return srv.getByProjection(ActorDTO.class);
 	}
	@GetMapping(path = "/{id}")
	public ActorDTO getOne(@PathVariable int id) throws NotFoundException {
		var item = srv.getOne(id);
		if (item.isEmpty()) {
			throw new NotFoundException("No se encontró el actor con id " + id);
		}
		return ActorDTO.from(item.get());
	}
//	
	//Java 17 clase que se crea a traves de datos

//	
	
record Titulo(int id, String titulo) {	}
 	
 	@GetMapping(path = "/{id}/pelis")
 	@ApiResponse(responseCode = "200", description = "Películas encontradas exitosamente")
 	@ApiResponse(responseCode = "404", description = "Actor no encontrado")
 	@Transactional
 	public List<Titulo> getPeliculas(@PathVariable int id) throws NotFoundException {
 		var item = srv.getOne(id);
 		if (item.isEmpty()) {
 			throw new NotFoundException("No se encontró el actor con id " + id);
 		}
 		return item.get().getFilmActors().stream()
 				.map(o -> new Titulo(o.getFilm().getFilmId(), o.getFilm().getTitle()))
 				.toList();
 	}
 	@PostMapping
 	@ApiResponse(responseCode = "201", description = "Actor creado")
	public ResponseEntity<Object> create(@Valid @RequestBody ActorDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		var newItem = srv.add(ActorDTO.from(item));
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(newItem.getActorId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ApiResponse(responseCode = "204", description = "Actor actualizado exitosamente")
	@ApiResponse(responseCode = "400", description = "Solicitud incorrecta, el id no coincide")
	@ApiResponse(responseCode = "404", description = "Actor no encontrado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@PathVariable int id, @Valid @RequestBody ActorDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		if (item.getActorId() != id) {
			throw new BadRequestException("El id del actor no coincide con el recurso a modificar");
		}
		srv.modify(ActorDTO.from(item));
	}

	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "204", description = "Actor eliminado exitosamente")
	@ApiResponse(responseCode = "404", description = "Actor no encontrado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		srv.deleteById(id);
	}
}