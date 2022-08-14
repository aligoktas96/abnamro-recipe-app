package com.assignment.abnamro.common;

import com.assignment.abnamro.model.dto.request.CreateIngredientRequest;
import com.assignment.abnamro.model.dto.request.CreateRecipeRequest;
import com.assignment.abnamro.model.dto.request.GetRecipeRequest;
import com.assignment.abnamro.model.dto.request.UpdateRecipeRequest;
import com.assignment.abnamro.model.dto.response.RecipeResponse;
import com.assignment.abnamro.model.entity.Recipe;
import com.assignment.abnamro.model.entity.RecipeIngredient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataProvider {
    public CreateRecipeRequest getCreateRequest() {
        return CreateRecipeRequest.builder()
                .name("Nachos")
                .isVegetarian(false)
                .ingredient(List.of(CreateIngredientRequest.builder().name("Chicken").build(),
                        CreateIngredientRequest.builder().name("Cheddar").build()))
                .servings(2)
                .instruction("1. first step - 2. second step")
                .build();
    }

    public UpdateRecipeRequest getUpdateRecipeRequest() {
        return UpdateRecipeRequest.builder()
                .name("Nachos updated")
                .isVegetarian(false)
                .instruction("1. first step - 2. second step")
                .servings(2)
                .build();
    }

    public RecipeResponse getRecipeResponse() {
        return RecipeResponse.builder()
                .name("Pizza")
                .isVegetarian(false)
                .instruction("1. first step - 2. second step")
                .servings(2)
                .build();
    }

    public GetRecipeRequest getRecipeRequest() {
        return GetRecipeRequest.builder()
                .name("Pasta")
                .isVegetarian(true)
                .instruction("1. first step - 2. second step")
                .servings(2)
                .build();
    }

    public Recipe getRecipe() {
        return Recipe.builder()
                .name("Nachos")
                .isVegetarian(false)
                .instruction("1. first step - 2. second step")
                .ingredient(List.of(RecipeIngredient.builder().name("Chicken").build(),
                        RecipeIngredient.builder().name("Cheddar").build()))
                .servings(2)
                .build();
    }
}
