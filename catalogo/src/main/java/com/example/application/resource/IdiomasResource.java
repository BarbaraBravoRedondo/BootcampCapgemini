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

import com.example.domains.contracts.services.IdiomasService;
import com.example.domains.entities.dtos.LanguageDTO;
import com.example.exceptions.BadRequestException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/idiomas/v1")
@Tag(name = "idioma-service", description = "Gestión de idiomas")
public class IdiomasResource {
    
    private final IdiomasService srv;

    public IdiomasResource(IdiomasService srv) {
    	super();
        this.srv = srv;
    }

    
	@GetMapping
 	
 	public List<LanguageDTO> getAll() {
 		return srv.getByProjection(LanguageDTO.class);
 	}
	

	
	@GetMapping(path = "/{id}")
	@ApiResponse(responseCode = "200", description = "Idioma encontrado exitosamente")
	@ApiResponse(responseCode = "404", description = "Idioma no encontrado")
	public LanguageDTO getLanguageId(@PathVariable int id) throws NotFoundException {
	    var item = srv.getOne(id);
	    if (item.isEmpty()) {
	        throw new NotFoundException("No se encontró el idioma con id " + id);
	    }
	    return LanguageDTO.from(item.get());  
	}

	 @PostMapping
	
	    @ApiResponse(responseCode = "201", description = "Idioma creado exitosamente")
	    public ResponseEntity<Object> create(@Valid @RequestBody LanguageDTO item) throws BadRequestException, DuplicateKeyException, InvalidDataException {
	        var newItem = srv.add(LanguageDTO.from(item));  
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                .buildAndExpand(newItem.getLanguageId()).toUri();
	        return ResponseEntity.created(location).build();
	    }


	 @PutMapping("/{id}")
	    
	    @ApiResponse(responseCode = "204", description = "Idioma actualizado exitosamente")
	    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta, el id no coincide")
	    @ApiResponse(responseCode = "404", description = "Idioma no encontrado")
	    @ResponseStatus(HttpStatus.NO_CONTENT)
	    public void update(@PathVariable int id, @Valid @RequestBody LanguageDTO item) throws BadRequestException, NotFoundException, InvalidDataException {
	        if (item.getLanguageId() != id) {
	            throw new BadRequestException("El id del idioma no coincide con el recurso a modificar");
	        }
	        srv.modify(LanguageDTO.from(item));  
	    }

    @DeleteMapping("/{id}")
    
    @ApiResponse(responseCode = "204", description = "Idioma eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Idioma no encontrado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        srv.deleteById(id);
    }
}
