package com.example.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.example.domains.core.contracts.repositories.RepositoryWithProjections;
import com.example.domains.entities.Language;

public interface IdiomasRepository extends JpaRepository<Language, Integer>, JpaSpecificationExecutor<Language>,RepositoryWithProjections {



}
