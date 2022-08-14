package com.assignment.abnamro.model.dto.request;

import com.assignment.abnamro.model.entity.RecipeIngredient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetRecipeRequest {

    private String name;

    private Boolean isVegetarian;

    private List<RecipeIngredient> ingredient;

    private int servings;

    private String instruction;
}
