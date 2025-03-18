package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.ActoresRepository;
import com.example.domains.contracts.services.ActoresService;
import com.example.domains.entities.Actor;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class ActoresServiceImpl implements ActoresService {
	private ActoresRepository dao;
	
	public ActoresServiceImpl(ActoresRepository dao) {
		this.dao = dao;
	}
	

	@Override
	public List<Actor> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Actor> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Actor add(Actor item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("El actor no puede ser nulo");
		}
		 if (dao.existsById(item.getActorId())) {
		        throw new DuplicateKeyException("Actor con ID " + item.getActorId() + " ya existe.");
		    }
		  return dao.save(item);
		
	}
	
	 @Transactional
	@Override
	public Actor modify(Actor item) throws NotFoundException, InvalidDataException {
	   
	    if (item == null || item.getActorId() == 0) {
	        throw new InvalidDataException("Actor inválido o con ID incorrecto");
	    }
	    if (!dao.existsById(item.getActorId())) {
	        throw new NotFoundException("Actor no encontrado con ID: " + item.getActorId());
	    }
	 
	    if (item.isInvalid()) {
	        throw new InvalidDataException(item.getErrorsMessage());
	    }
	    return dao.save(item);
	}

	@Override
	public void delete(Actor item) throws InvalidDataException {
		  if (item == null || item.getActorId() == 0) {
	            throw new InvalidDataException("Actor no econtrado o con ID incorrecto");
	        }
	        dao.delete(item);

	}
	
	@Override
    public void deleteById(Integer id) {
      

        dao.deleteById(id);  }
	
	@Override
	public void repartePremios() {
		 System.out.println("Repartiendo premios...");

	}

}
