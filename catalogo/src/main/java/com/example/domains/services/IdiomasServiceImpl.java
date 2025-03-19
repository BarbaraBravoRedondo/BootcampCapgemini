package com.example.domains.services;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.IdiomasRepository;
import com.example.domains.contracts.services.IdiomasService;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class IdiomasServiceImpl implements IdiomasService {
	private IdiomasRepository dao;
	
	public IdiomasServiceImpl(IdiomasRepository dao) {
		this.dao = dao;
	}

//	@Override
//	public List<Language> getAll() {
//		return dao.findAll();
//	}
	@Transactional
	@Override
	public List<Language> getAll() {
	    System.out.println("Llamando a getAll()...");
	    List<Language> languages = dao.findAll();

	    // Imprimir todos los idiomas que obtenemos de la base de datos
	    for (Language language : languages) {
	        System.out.println("Idioma: " + language.getLanguageId() + " - " + language.getName());
	    }

	    return languages;
	}
	@Override
	public Optional<Language> getOne(Integer id) {
		return dao.findById(id);
	}

	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
	    if(item == null) {
	        throw new InvalidDataException("El idioma no puede ser nulo");
	    }
	    if (dao.existsById(item.getLanguageId())) {
	        throw new DuplicateKeyException("Idioma ya existe con el ID: " + item.getLanguageId());
	    }
	    return dao.save(item);
	}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
	if (item ==null || item.getLanguageId() == 0){
		 throw new InvalidDataException ("Idioma invalido o con ID incorrecto");}	 if (!dao.existsById(item.getLanguageId())) {        throw new NotFoundException("Actor no encontrado con ID: " + item.getLanguageId());
    }
    return dao.save(item);
	
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
	  if (item == null || item.getLanguageId() == 0) {
	            throw new InvalidDataException("Idioma no econtrado o con ID incorrecto");
	        }
	        dao.delete(item);

	}
	
	@Override
   public void deleteById(Integer id) {
     

        dao.deleteById(id);  }
	
	@Override
	public <T> List<T> getByProjection(Class<T> type) {
		return dao.findAllBy(type);
	}

	@Override
	public <T> Iterable<T> getByProjection(Sort sort, Class<T> type) {
		return dao.findAllBy(sort, type);
	}

	@Override
	public <T> Page<T> getByProjection(Pageable pageable, Class<T> type) {
		return dao.findAllBy(pageable, type);
	}

	@Override
	public Iterable<Language> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Language> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}


}
