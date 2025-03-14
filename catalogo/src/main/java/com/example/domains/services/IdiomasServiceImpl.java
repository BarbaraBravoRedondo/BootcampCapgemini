package com.example.domains.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.domains.contracts.repositories.IdiomasRepository;
import com.example.domains.contracts.services.IdiomasService;
import com.example.domains.entities.Language;
import com.example.exceptions.DuplicateKeyException;
import com.example.exceptions.InvalidDataException;
import com.example.exceptions.NotFoundException;

@Service
public class IdiomasServiceImpl implements IdiomasService {
	private IdiomasRepository dao;
	
	public IdiomasServiceImpl(IdiomasRepository dao) {
		this.dao = dao;
	}

	@Override
	public List<Language> getAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Language> getOne(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Language add(Language item) throws DuplicateKeyException, InvalidDataException {
		if(item == null) {
			throw new InvalidDataException("El actor no puede ser nulo");
		}
		return null;
}

	@Override
	public Language modify(Language item) throws NotFoundException, InvalidDataException {
	if (item ==null || item.getLanguageId() == 0){
		 throw new InvalidDataException ("Actor inválido o con ID incorrecto");}	 if (!dao.existsById(item.getLanguageId())) {        throw new NotFoundException("Actor no encontrado con ID: " + item.getLanguageId());
    }
    return dao.save(item);
	
	}

	@Override
	public void delete(Language item) throws InvalidDataException {
	  if (item == null || item.getLanguageId() == 0) {
	            throw new InvalidDataException("Actor no econtrado o con ID incorrecto");
	        }
	        dao.delete(item);

	}
	
	@Override
   public void deleteById(Integer id) {
     

        dao.deleteById(id);  }


}
