package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.entities.Language;

public interface IdiomasRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language> {
	List<Language> findByName(String name);

	List<Language> findTop5ByNameStartingWith(String prefijo, Sort orderBy);

	List<Language> findBylanguageIdGreaterThan(int id);

	@Query(value = "SELECT a FROM Language a WHERE a.languageId > ?1")
	List<Language> findlastUpdateJPQL(int id);

	@Query(value = "SELECT O* FROM language a WHERE a.language_id > :id", nativeQuery = true)
	List<Language> findNovedadesSQL(int id);

	@Query("SELECT l FROM Language l LEFT JOIN FETCH l.films f LEFT JOIN FETCH l.filmsVO v")
	List<Language> findAllLanguagesWithFilms();

	@Query("SELECT DISTINCT l FROM Language l JOIN l.films f WHERE f.language = ?1 ORDER BY l.name")
	List<Language> findLanguagesByFilmLanguage(Language language);

}
