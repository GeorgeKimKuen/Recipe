package com.vaadin.tutorial.crm.backend.service;

import com.vaadin.tutorial.crm.backend.entity.recipe;
import com.vaadin.tutorial.crm.backend.repository.RecipesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipesService {
    private RecipesRepository recipesRepository;

    public RecipesService(RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<recipe> findAll() {
        return recipesRepository.findAll();
    }
}