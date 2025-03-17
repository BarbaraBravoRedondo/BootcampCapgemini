package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.CategoriasRepository;
import com.example.domains.contracts.services.CategoriasService;
import com.example.domains.entities.Category;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class CategoriasServiceImpl implements CategoriasService {
    private CategoriasRepository dao;

    public CategoriasServiceImpl(CategoriasRepository dao) {
        this.dao = dao;
    }

    @Override
    public List<Category> getAll() {
        return dao.findAll();
    }

    @Override
    public Optional<Category> getOne(Integer id) {
        return dao.findById(id);
    }

    @Override
    public Category add(Category item) throws DuplicateKeyException, InvalidDataException {
        if (item == null) {
            throw new InvalidDataException("La categoría no puede ser nula");
        }
        if (dao.existsById(item.getCategoryId())) {
            throw new DuplicateKeyException("Categoría ya existe con el ID: " + item.getCategoryId());
        }
        return dao.save(item);
    }

    @Override
    public Category modify(Category item) throws NotFoundException, InvalidDataException {
        if (item == null || item.getCategoryId() == 0) {
            throw new InvalidDataException("Categoría inválida o con ID incorrecto");
        }
        if (!dao.existsById(item.getCategoryId())) {
            throw new NotFoundException("Categoría no encontrada con ID: " + item.getCategoryId());
        }
        return dao.save(item);
    }

    @Override
    public void delete(Category item) throws InvalidDataException {
        if (item == null || item.getCategoryId() == 0) {
            throw new InvalidDataException("Categoría no encontrada o con ID incorrecto");
        }
        dao.delete(item);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }
}
