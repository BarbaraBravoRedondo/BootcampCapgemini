package com.example.domains.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.entities.Film;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.repositories.PeliculasRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PeliculasServiceImplTest {

    @Mock
    private PeliculasRepository peliculasRepository;

    private PeliculasServiceImpl peliculasService;

    @BeforeEach
    public void setUp() {
        peliculasService = new PeliculasServiceImpl(peliculasRepository);
    }

    @Test
    @DisplayName("Test traer todas las películas")
    void testGetAll() {
        Film film1 = new Film();
        Film film2 = new Film();
        List<Film> films = List.of(film1, film2);
        when(peliculasRepository.findAll()).thenReturn(films);

        List<Film> result = peliculasService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("La Casa de Papel", result.get(0).getTitle());
    }

    @Test
    @DisplayName("Test obtener película por Id")
    void testGetOne() {
        Film film = new Film();
        when(peliculasRepository.findById(1)).thenReturn(Optional.of(film));
        Optional<Film> result = peliculasService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals("La Casa de Papel", result.get().getTitle());
    }

    @Test
    @DisplayName("Agregar: InvalidDataException cuando la película es null")
    void testAddNullFilm() {
        assertThrows(InvalidDataException.class, () -> peliculasService.add(null));
    }

    @Test
    @DisplayName("Agregar: DuplicateKeyException cuando la película ya existe")
    void testAddDuplicateFilm() {
        Film film = new Film();
        when(peliculasRepository.existsById(film.getFilmId())).thenReturn(true);

        assertThrows(DuplicateKeyException.class, () -> peliculasService.add(film));
    }

    @Test
    @DisplayName("Modificar: NotFoundException cuando la película no existe")
    void testModifyFilmNotFound() {
        Film film = new Film();
        when(peliculasRepository.existsById(film.getFilmId())).thenReturn(false);

        assertThrows(NotFoundException.class, () -> peliculasService.modify(film));
    }

    @Test
    @DisplayName("Modificar: InvalidDataException cuando la película tiene ID igual a 0")
    void testModifyFilmWithInvalidId() {
        Film film = new Film();
        assertThrows(InvalidDataException.class, () -> peliculasService.modify(film));
    }

    @Test
    @DisplayName("Eliminar: InvalidDataException cuando la película es nula")
    void testDeleteInvalidFilm() {
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(null));
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(new Film()));
    }

    @Test
    @DisplayName("Eliminar una película válida")
    void testDeleteValidFilm() throws InvalidDataException {
        Film film = new Film();  

        peliculasService.delete(film); 

      
        verify(peliculasRepository, times(1)).deleteById(film.getFilmId());
    }


    @Test
    @DisplayName("Eliminar: InvalidDataException cuando la película tiene ID igual a 0")
    void testDeleteFilmWithInvalidId() {
        Film film = new Film();
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(film));
    }

    @Test
    @DisplayName("Eliminar por Id")
    void testDeleteById() {
        peliculasService.deleteById(1);
        verify(peliculasRepository, times(1)).deleteById(1);
    }
}
