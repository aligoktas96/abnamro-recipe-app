package com.assignment.abnamro.model.dto.response;

import com.assignment.abnamro.model.entity.RecipeIngredient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeResponse {
    private String name;
    private Boolean isVegetarian;
    private List<RecipeIngredient> ingredient;
    private int servings;
    private String instruction;
}
