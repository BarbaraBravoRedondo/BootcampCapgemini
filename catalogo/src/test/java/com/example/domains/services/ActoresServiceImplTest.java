package com.example.domains.services;
import static org.mockito.Mockito.*;

import com.example.domains.entities.Actor;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.repositories.ActoresRepository;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ActoresServiceImplTest {

    @Mock
    private ActoresRepository actoresRepository;
 
    private ActoresServiceImpl actoresService;

    @BeforeEach
    public void setUp() {
        actoresService = new ActoresServiceImpl(actoresRepository);
    }

  
    @Test
    @DisplayName("Test traer todos los actores")
    void testGetAll() {
        Actor actor1 = new Actor(1, "John", "Doe");
        Actor actor2 = new Actor(2, "Jane", "Doe");
        List<Actor> actors = List.of(actor1, actor2);
        when(actoresRepository.findAll()).thenReturn(actors);

        List<Actor> result = actoresService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

   
    @Test
    @DisplayName("Test por Id")
    void testGetOne() {
        Actor actor = new Actor(1, "John", "Doe");
        when(actoresRepository.findById(1)).thenReturn(Optional.of(actor));
        Optional<Actor> result = actoresService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    
    @Test
    @DisplayName("Add: InvalidDataException cuando actor es null")
    void testAddNullActor() {
        assertThrows(InvalidDataException.class, () -> actoresService.add(null));
    }

   
    @Test
    @DisplayName("Add: DuplicateKeyException cuando el actor ya existe")
    void testAddDuplicateActor() throws InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        when(actoresRepository.existsById(actor.getActorId())).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> actoresService.add(actor));
    }

  
    @Test
    @DisplayName("Modify: NotFoundException cuando el actor no existe")
    void testModifyActorNotFound() throws InvalidDataException {
        Actor actor = new Actor(999, "Non-existing Actor", "Description");
        when(actoresRepository.existsById(actor.getActorId())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> actoresService.modify(actor));
    }

 
    @Test
    @DisplayName("Modify: InvalidDataException cuando el actor tiene ID igual a 0")
    void testModifyActorWithInvalidId() {
        Actor actor = new Actor(0, "Invalid Actor", "Description");
        assertThrows(InvalidDataException.class, () -> actoresService.modify(actor));
    }

   
    @Test
    @DisplayName("Delete: InvalidDataException cuando actor es null")
    void testDeleteInvalidActor() {
        assertThrows(InvalidDataException.class, () -> actoresService.delete(null));
        assertThrows(InvalidDataException.class, () -> actoresService.delete(new Actor(0, "Invalid", "Actor")));
    }

   
    @Test
    @DisplayName("Elimina un actor valido")
    void testDeleteValidActor() throws InvalidDataException {
        Actor actor = new Actor(1, "John", "Doe");
        actoresService.delete(actor);
        verify(actoresRepository, times(1)).delete(actor);
    }

  
    @Test
    @DisplayName("Delete: InvalidDataException cuando el actor tiene ID igual a 0")
    void testDeleteActorWithInvalidId() {
        Actor actor = new Actor(0, "Invalid Actor", "Description");
        assertThrows(InvalidDataException.class, () -> actoresService.delete(actor));
    }

   
    @Test
    @DisplayName("Eliminar por Id")
    void testDeleteById() {
        actoresService.deleteById(1);
        verify(actoresRepository, times(1)).deleteById(1);
    }
}
