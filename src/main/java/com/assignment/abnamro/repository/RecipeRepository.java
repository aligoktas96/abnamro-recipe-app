package com.assignment.abnamro.repository;

import com.assignment.abnamro.model.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("select r from Recipe r JOIN r.ingredient i where i.name = ?1")
    List<Recipe> findByIngredients(String name);
}
