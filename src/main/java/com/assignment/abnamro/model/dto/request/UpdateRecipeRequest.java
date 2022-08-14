package com.assignment.abnamro.model.dto.request;

import com.assignment.abnamro.model.entity.RecipeIngredient;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UpdateRecipeRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Boolean isVegetarian;

    @NotNull
    private List<RecipeIngredient> ingredient;

    @Positive
    private int servings;

    @Pattern(message="Type can contain alphanumeric characters only", regexp = "[a-zA-Z0-9 ]+")
    private String instruction;
}
