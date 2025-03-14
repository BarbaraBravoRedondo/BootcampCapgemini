package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Film;

public interface PeliculasRepository  extends JpaRepository<Film, Integer>, JpaSpecificationExecutor<Film> {
	List<Film> findTop5ByTitleStartingWithOrderByTitleDesc(String prefijo);
	List<Film> findTop5ByTitleStartingWith(String prefijo, Sort orderBy);
	
	List<Film> findByFilmIdGreaterThan(int id);
	@Query(value = "SELECT a FROM Film a WHERE a.filmId > ?1")
    List<Film> findDescriptionJPQL(int id);
    @Query(value = "SELECT * FROM film a WHERE a.film_id > :id", nativeQuery = true)
    List<Film> findNovedadesSQL(int id);

}
