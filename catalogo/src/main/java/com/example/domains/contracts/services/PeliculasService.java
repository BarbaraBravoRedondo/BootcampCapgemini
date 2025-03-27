package com.example.domains.contracts.services;


import com.example.domains.core.contracts.services.ProjectionDomainService;
import com.example.domains.core.contracts.services.SpecificationDomainService;
import com.example.domains.entities.Film;

public interface PeliculasService extends ProjectionDomainService<Film, Integer> ,SpecificationDomainService<Film, Integer>{
}
