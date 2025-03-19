package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Category;

public interface CategoriasRepository extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category>, RepositoryWithProjections{
	List<Category> findTop5ByNameStartingWithOrderByLastUpdateDesc(String prefijo);
	List<Category> findTop5ByNameStartingWith(String prefijo, Sort orderBy);
	List<Category> findByCategoryIdGreaterThan(int id);
	@Query(value = "SELECT a FROM Category a WHERE a.categoryId > ?1")
	List<Category> findLastUpdateJPQL(int id);
	@Query(value = "SELECT * FROM category a WHERE a.category_id > :id", nativeQuery = true)
	List<Category> findFilmCategoriesSQL(int id);

}
