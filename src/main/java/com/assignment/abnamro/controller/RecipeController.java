package com.assignment.abnamro.controller;

import com.assignment.abnamro.model.dto.request.CreateRecipeRequest;
import com.assignment.abnamro.model.dto.request.GetRecipeRequest;
import com.assignment.abnamro.model.dto.request.UpdateRecipeRequest;
import com.assignment.abnamro.model.dto.response.RecipeResponse;
import com.assignment.abnamro.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/")
    public ResponseEntity<List<RecipeResponse>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/byIngredient/{name}")
    public ResponseEntity<List<RecipeResponse>> getByIngredients(@NotNull @PathVariable("name") String name) {
        return ResponseEntity.ok(recipeService.getByIngredients(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<RecipeResponse>> getRecipes(@RequestBody GetRecipeRequest getRecipeRequest) {
        return ResponseEntity.ok(recipeService.getRecipe(getRecipeRequest));
    }


    @PostMapping("/create")
    public ResponseEntity<RecipeResponse> create(@RequestBody CreateRecipeRequest createRequest) {
        return new ResponseEntity<>(recipeService.createRecipe(createRequest), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<RecipeResponse> update(@NotNull @PathVariable("id") Long id, @Valid @RequestBody UpdateRecipeRequest updateRequest) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, updateRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@NotNull @PathVariable("id") Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }


}
