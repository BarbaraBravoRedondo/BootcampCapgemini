package com.example.domains.entities.dtos;

import org.springframework.beans.factory.annotation.Value;

public interface CategoryShort {
    @Value("#{target.categoryId}")
    int getId();

    @Value("#{target.name}")
    String getNombre();
}