package com.example.domains.services;
import static org.mockito.Mockito.*;

import com.example.domains.entities.Film;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.repositories.PeliculasRepository;
import static org.junit.jupiter.api.Assertions.*;

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
    	Film film1 = new Film(1, "La Casa de Papel", "Acción");
    	Film film2 = new Film(2, "Titanic", "Romántica");
        List<Film> films = List.of(film1, film2);
        when(peliculasRepository.findAll()).thenReturn(films);

        List<Film> result = peliculasService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("La Casa de Papel", result.get(0).getTitle());
    }

    @Test
    @DisplayName("Test por Id")
    void testGetOne() {
    	Film film = new Film(1, "La Casa de Papel", "Acción");
        when(peliculasRepository.findById(1)).thenReturn(Optional.of(film));
        Optional<Film> result = peliculasService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals("La Casa de Papel", result.get().getTitle());
    }

    @Test
    @DisplayName("Add: InvalidDataException cuando la película es null")
    void testAddNullFilm() {
        assertThrows(InvalidDataException.class, () -> peliculasService.add(null));
    }

    @Test
    @DisplayName("Add: DuplicateKeyException cuando la película ya existe")
    void testAddDuplicateFilm() throws InvalidDataException {
    	Film film = new Film(1, "La Casa de Papel", "Acción");
        when(peliculasRepository.existsById(film.getFilmId())).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> peliculasService.add(film));
    }

    @Test
    @DisplayName("Modify: NotFoundException cuando la película no existe")
    void testModifyFilmNotFound() throws InvalidDataException {
        Film film = new Film(999, "Non-existing Film", "Description");
        when(peliculasRepository.existsById(film.getFilmId())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> peliculasService.modify(film));
    }

    @Test
    @DisplayName("Modify: InvalidDataException cuando la película tiene ID igual a 0")
    void testModifyFilmWithInvalidId() {
        Film film = new Film(0, "Pelicula Invalida", "Drama");
        assertThrows(InvalidDataException.class, () -> peliculasService.modify(film));
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando la película es nula")
    void testDeleteInvalidFilm() {
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(null));
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(new Film(0, "Pelicula Invalida", "Drama")));
    }

    @Test
    @DisplayName("Elimina una película válida")
    void testDeleteValidFilm() throws InvalidDataException {
    	Film film = new Film(1, "La Casa de Papel", "Acción");
        peliculasService.delete(film);
        verify(peliculasRepository, times(1)).delete(film);
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando la película tiene ID igual a 0")
    void testDeleteFilmWithInvalidId() {
        Film film = new Film(0, "Pelicula Invalida", "Drama");
        assertThrows(InvalidDataException.class, () -> peliculasService.delete(film));
    }

    @Test
    @DisplayName("Eliminar por Id")
    void testDeleteById() {
        peliculasService.deleteById(1);
        verify(peliculasRepository, times(1)).deleteById(1);
    }
}
