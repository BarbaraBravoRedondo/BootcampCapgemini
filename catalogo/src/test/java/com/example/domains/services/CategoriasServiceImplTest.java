package com.example.domains.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;
import com.example.domains.contracts.repositories.CategoriasRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoriasServiceImplTest {

    @Mock
    private CategoriasRepository categoriasRepository;

    private CategoriasServiceImpl categoriasService;

    @BeforeEach
    public void setUp() {
        categoriasService = new CategoriasServiceImpl(categoriasRepository);
    }

    @Test
    @DisplayName("Test traer todas las categorías")
    void testGetAll() {
        Category category1 = new Category(1, "Drama");
        Category category2 = new Category(2, "Comedy");
        List<Category> categories = List.of(category1, category2);
        when(categoriasRepository.findAll()).thenReturn(categories);

        List<Category> result = categoriasService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Drama", result.get(0).getName());
    }

    @Test
    @DisplayName("Test por ID")
    void testGetOne() {
        Category category = new Category(1, "Action");
        when(categoriasRepository.findById(1)).thenReturn(Optional.of(category));
        Optional<Category> result = categoriasService.getOne(1);

        assertTrue(result.isPresent());
        assertEquals("Action", result.get().getName());
    }

    @Test
    @DisplayName("Add: InvalidDataException cuando la categoría es nula")
    void testAddNullCategory() {
        assertThrows(InvalidDataException.class, () -> categoriasService.add(null));
    }

    @Test
    @DisplayName("Add: DuplicateKeyException cuando la categoría ya existe")
    void testAddDuplicateCategory() throws InvalidDataException {
        Category category = new Category(1, "Drama");
        when(categoriasRepository.existsById(category.getCategoryId())).thenReturn(true);
        assertThrows(DuplicateKeyException.class, () -> categoriasService.add(category));
    }

    @Test
    @DisplayName("Modify: NotFoundException cuando la categoría no existe")
    void testModifyCategoryNotFound() throws InvalidDataException {
        Category category = new Category(999, "Non-existing Category");
        when(categoriasRepository.existsById(category.getCategoryId())).thenReturn(false);
        assertThrows(NotFoundException.class, () -> categoriasService.modify(category));
    }

    @Test
    @DisplayName("Modify: InvalidDataException cuando la categoría tiene ID igual a 0")
    void testModifyCategoryWithInvalidId() {
        Category category = new Category(0, "Invalid Category");
        assertThrows(InvalidDataException.class, () -> categoriasService.modify(category));
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando la categoría es nula")
    void testDeleteInvalidCategory() {
        assertThrows(InvalidDataException.class, () -> categoriasService.delete(null));
        assertThrows(InvalidDataException.class, () -> categoriasService.delete(new Category(0, "Invalid Category")));
    }

    @Test
    @DisplayName("Delete: Elimina una categoría válida")
    void testDeleteValidCategory() throws InvalidDataException {
        Category category = new Category(1, "Action");
        categoriasService.delete(category);
        verify(categoriasRepository, times(1)).delete(category);
    }

    @Test
    @DisplayName("Delete: InvalidDataException cuando la categoria tiene ID igual a 0")
    void testDeleteCategoryWithInvalidId() {
        Category category = new Category(0, "Invalid Category");
        assertThrows(InvalidDataException.class, () -> categoriasService.delete(category));
    }

    @Test
    @DisplayName("Eliminar por ID")
    void testDeleteById() {
        categoriasService.deleteById(1);
        verify(categoriasRepository, times(1)).deleteById(1);
    }
}
