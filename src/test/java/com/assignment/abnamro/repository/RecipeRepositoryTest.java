package com.assignment.abnamro.repository;

import com.assignment.abnamro.TestInitializer;
import com.assignment.abnamro.common.DataProvider;
import com.assignment.abnamro.model.entity.Recipe;
import com.assignment.abnamro.model.entity.RecipeIngredient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@DataJpaTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@ContextConfiguration(initializers = TestInitializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecipeRepositoryTest {

    @Spy
    DataProvider dataProvider;

    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    void shouldCreateRecipe() {
        Recipe recipe = dataProvider.getRecipe();

        Assertions.assertNull(recipe.getId());
        recipeRepository.save(recipe);
        Assertions.assertNotNull(recipe.getId());
    }

    @Test
    void shouldUpdateRecipe() {
        String beforeName = "before update";
        Recipe recipe = dataProvider.getRecipe();

        recipeRepository.save(recipe);

        Recipe updated = dataProvider.getRecipe();

        updated.setId(recipe.getId());
        updated.setName(beforeName);

        recipeRepository.save(updated);

        String updatedName = recipeRepository.findById(recipe.getId()).get().getName();
        Assertions.assertEquals(updatedName, beforeName);
    }

    @Test
    void shouldDeleteRecipe(){
        Recipe recipe = dataProvider.getRecipe();

        recipeRepository.save(recipe);

        recipeRepository.deleteById(recipe.getId());

        boolean isPresent = recipeRepository.findById(recipe.getId()).isPresent();

        Assertions.assertFalse(isPresent);
    }

    @Test
    void shouldGetRecipeAccordingToIngredientsName() {
        Recipe pizzaRecipe = Recipe.builder()
                .name("Pizza")
                .ingredient(List.of(RecipeIngredient.builder()
                        .name("Cheese")
                        .build(),RecipeIngredient.builder()
                        .name("Tomato")
                        .build()))
                .build();

        Recipe recipeCheesecake = Recipe.builder()
                .name("Cheesecake")
                .ingredient(List.of(RecipeIngredient.builder()
                        .name("Cheese")
                        .build(),RecipeIngredient.builder()
                        .name("Cake")
                        .build()))
                .build();

        recipeRepository.save(pizzaRecipe);

        recipeRepository.save(recipeCheesecake);

        List<Recipe> cheese = recipeRepository.findByIngredients("Cheese");

        Assertions.assertNotNull(cheese);

        Assertions.assertEquals(2, cheese.size());

        Assertions.assertEquals(cheese.get(0).getName(),"Pizza");

        Assertions.assertEquals(cheese.get(1).getName(),"Cheesecake");

    }

}
