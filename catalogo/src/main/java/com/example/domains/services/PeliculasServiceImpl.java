package com.example.domains.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.PeliculasRepository;
import com.example.domains.contracts.services.PeliculasService;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Film;
import com.example.domains.entities.dtos.FilmShort;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class PeliculasServiceImpl implements PeliculasService {
	private PeliculasRepository dao;
	
	public PeliculasServiceImpl(PeliculasRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Film> getAll() {
		return dao.findAll();
	}

    @Autowired
    private PeliculasRepository filmRepository;

   
    public List<FilmShort> getFilmsShort() {
        return filmRepository.findAllFilmShort();
    }

	@Override
	public Optional<Film> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Film add(Film item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("La pelicula no puede ser nulo");
		}
		   if (dao.existsById(item.getFilmId())) {
		        throw new DuplicateKeyException("Película con ID " + item.getFilmId() + " ya existe.");
		    }
		return null;
}

	@Override

	public Film modify(Film item) throws NotFoundException, InvalidDataException {
	   
	    if (item == null || item.getFilmId() == 0) {
	        throw new InvalidDataException("Pelicula invalida o con ID incorrecto");
	    }
	    if (!dao.existsById(item.getFilmId())) {
	        throw new NotFoundException("Pelicula no encontrada con ID: " + item.getFilmId());
	    }
	 
	    return dao.save(item);
	}

	@Override
	public void delete(Film item) throws InvalidDataException {
	  if (item == null || item.getFilmId() == 0) {
	            throw new InvalidDataException("Pelicula no econtrado o con ID incorrecto");
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
	public Iterable<Film> getAll(Sort sort) {
		return dao.findAll(sort);
	}

	@Override
	public Page<Film> getAll(Pageable pageable) {
		return dao.findAll(pageable);
	}


}
