package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.example.domains.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Actor;
import com.example.domains.entities.Category;


@RepositoryRestResource(path="actores", collectionResourceRel="actores", itemResourceRel="actor")
public interface CategoryRepository extends JpaRepository<Category , Integer>, JpaSpecificationExecutor<Actor>,RepositoryWithProjections{
	
	@RestResource(path="por-nombre")
	List<Actor>findTop5ByFirstNameStartingWithOrderByLastNameDesc( String prefijo);


}
