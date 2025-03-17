package com.example.domains.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.entities.Language;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.repositories.IdiomasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class IdiomasServiceImplTest {

    @Mock
    private IdiomasRepository idiomasRepository;

    private IdiomasServiceImpl idiomasService;

    @BeforeEach
    public void setUp() {
        idiomasService = new IdiomasServiceImpl(idiomasRepository);
    }

    @Test
    @DisplayName("Test traer todos los idiomas")
    void testGetAll() {
        Language language1 = new Language();
        language1.setLanguageId(1);
        language1.setName("English");

        Language language2 = new Language();
        language2.setLanguageId(2);
        language2.setName("Spanish");

        List<Language> languages = List.of(language1, language2);
        when(idiomasRepository.findAll()).thenReturn(languages);

        List<Language> result = idiomasService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("English", result.get(0).getName());
    }

    @Test
    @DisplayName("Test por Id")
    void testGetOne() {
        Language language = new Language();
        language.setLanguageId(1);
        language.setName("English");

        when(idiomasRepository.findById(1)).thenReturn(Optional.of(language));
        Optional<Language> result = idiomasService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals("English", result.get().getName());
    }

    @Test
    @DisplayName("Add: InvalidDataException cuando el idioma es null")
    void testAddNullLanguage() {
        assertThrows(InvalidDataException.class, () -> idiomasService.add(null));
    }

    @Test
    @DisplayName("Add: DuplicateKeyException cuando el idioma ya existe")
    void testAddDuplicateLanguage() throws InvalidDataException {
        Language language = new Language();
        language.setLanguageId(1);
        language.setName("English");

        when(idiomasRepository.existsById(language.getLanguageId())).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> idiomasService.add(language));
    }

    @Test
    @DisplayName("Modify: NotFoundException cuando el idioma no existe")
    void testModifyLanguageNotFound() throws InvalidDataException {
        Language language = new Language();
        language.setLanguageId(999);
        language.setName("Non-existing Language");

        when(idiomasRepository.existsById(language.getLanguageId())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> idiomasService.modify(language));
    }

    @Test
    @DisplayName("Modify: InvalidDataException cuando el idioma tiene ID igual a 0")
    void testModifyLanguageWithInvalidId() {
        Language language = new Language();
        language.setLanguageId(0);
        language.setName("Invalid Language");

        assertThrows(InvalidDataException.class, () -> idiomasService.modify(language));
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando idioma es null")
    void testDeleteInvalidLanguage() {
        assertThrows(InvalidDataException.class, () -> idiomasService.delete(null));
        assertThrows(InvalidDataException.class, () -> idiomasService.delete(new Language()));  // idioma con ID 0
    }

    @Test
    @DisplayName("Elimina un idioma valido")
    void testDeleteValidLanguage() throws InvalidDataException {
        Language language = new Language();
        language.setLanguageId(1);
        language.setName("English");

        idiomasService.delete(language);
        verify(idiomasRepository, times(1)).delete(language);
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando el idioma tiene ID igual a 0")
    void testDeleteLanguageWithInvalidId() {
        Language language = new Language();
        language.setLanguageId(0);
        language.setName("Invalid Language");

        assertThrows(InvalidDataException.class, () -> idiomasService.delete(language));
    }

    @Test
    @DisplayName("Eliminar por Id")
    void testDeleteById() {
        Integer languageId = 1;
        idiomasService.deleteById(languageId);
        verify(idiomasRepository, times(1)).deleteById(languageId);
    }
}
