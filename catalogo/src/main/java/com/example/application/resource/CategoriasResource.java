package com.example.application.resource;

import java.net.URI;
import java.util.List;

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

import com.example.domains.contracts.services.CategoriasService;
import com.example.domains.entities.dtos.CategoryDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
@RestController
@RequestMapping("/categorias/v1")
public class CategoriasResource {
	 private final CategoriasService srv;

	    public CategoriasResource(CategoriasService srv) {
	    	super();
	        this.srv = srv;
	    }

		@GetMapping
	 	
	 	public List<CategoryDTO> getAll() {
	 		return srv.getByProjection(CategoryDTO.class);
	 	}
		

		
		@GetMapping(path = "/{id}")
		@ApiResponse(responseCode = "200", description = "Idioma encontrado exitosamente")
		@ApiResponse(responseCode = "404", description = "Idioma no encontrado")
		public CategoryDTO getCategoryId(@PathVariable int id) throws NotFoundException {
		    var item = srv.getOne(id);
		    if (item.isEmpty()) {
		        throw new NotFoundException("No se encontró el idioma con id " + id);
		    }
		    return CategoryDTO.from(item.get());  
		}

		 @PostMapping
		
		    @ApiResponse(responseCode = "201", description = "Categoria creada exitosamente")
		    public ResponseEntity<Object> create(@Valid @RequestBody CategoryDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
		        var newItem = srv.add(CategoryDTO.from(item));  
		        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		                .buildAndExpand(newItem.getCategoryId()).toUri();
		        return ResponseEntity.created(location).build();
		    }


		 @PutMapping("/{id}")
		    
		    @ApiResponse(responseCode = "204", description = "Idioma actualizado exitosamente")
		    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, el id no coincide")
		    @ApiResponse(responseCode = "404", description = "Idioma no encontrado")
		    @ResponseStatus(HttpStatus.NO_CONTENT)
		    public void update(@PathVariable int id, @Valid @RequestBody CategoryDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
		        if (item.getCategoryId() != id) {
		            throw new BadRequestException("El id del idioma no coincide con el recurso a modificar");
		        }
		        srv.modify(CategoryDTO.from(item));  
		    }

	    @DeleteMapping("/{id}")
	    
	    @ApiResponse(responseCode = "204", description = "Idioma eliminado exitosamente")
	    @ApiResponse(responseCode = "404", description = "Idioma no encontrado")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void delete(@PathVariable int id) {
	        srv.deleteById(id);
	    }

}
