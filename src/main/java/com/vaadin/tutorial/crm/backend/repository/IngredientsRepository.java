package com.vaadin.tutorial.crm.backend.repository;

import com.vaadin.tutorial.crm.backend.entity.ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientsRepository extends JpaRepository<ingredient,Long> {
    @Query("select i from ingredient i " +
            "where lower(i.ingredient) like lower(concat('%', :searchTerm, '%')) ")
    List<ingredient> search(@Param("searchTerm") String searchTerm);
}

