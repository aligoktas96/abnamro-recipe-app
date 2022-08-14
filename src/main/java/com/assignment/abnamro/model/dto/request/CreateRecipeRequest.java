package com.assignment.abnamro.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateRecipeRequest {

    @NotEmpty
    private String name;

    @NotNull
    private Boolean isVegetarian;

    @NotNull
    private List<CreateIngredientRequest> ingredient;

    @Positive
    private int servings;

    @Pattern(message="Type can contain alphanumeric characters only", regexp = "[a-zA-Z0-9 ]+")
    private String instruction;
}
