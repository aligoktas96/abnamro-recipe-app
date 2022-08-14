package com.assignment.abnamro.service;

import com.assignment.abnamro.exception.ApiErrorCode;
import com.assignment.abnamro.exception.BusinessException;
import com.assignment.abnamro.model.dto.request.CreateRecipeRequest;
import com.assignment.abnamro.model.dto.request.GetRecipeRequest;
import com.assignment.abnamro.model.dto.request.UpdateRecipeRequest;
import com.assignment.abnamro.model.dto.response.RecipeResponse;
import com.assignment.abnamro.model.entity.Recipe;
import com.assignment.abnamro.model.mapper.Mapper;
import com.assignment.abnamro.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    private final Mapper mapper;

    public List<RecipeResponse> getAllRecipes() {
        List<Recipe> all = recipeRepository.findAll();
        return all
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<RecipeResponse> getByIngredients(String name) {
        List<Recipe> all = recipeRepository.findByIngredients(name);
        return all
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public List<RecipeResponse> getRecipe(GetRecipeRequest getRecipeRequest) {
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();
        Example<Recipe> exampleQuery = Example.of(mapper.getDTOToEntity(getRecipeRequest), matcher);
        return recipeRepository.findAll(exampleQuery)
                .stream()
                .map(mapper::entityToResponse)
                .collect(Collectors.toList());
    }

    public RecipeResponse createRecipe(CreateRecipeRequest createRecipeRequest) {
        Recipe newRecipe = mapper.createDTOToEntity(createRecipeRequest);
        return mapper.entityToResponse(recipeRepository.save(newRecipe));
    }

    public RecipeResponse updateRecipe(Long id, UpdateRecipeRequest updateRecipeRequest) {
        Recipe toBeUpdated = recipeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ApiErrorCode.RECIPE_NOT_FOUND));

        Recipe updatedRecipe = mapper.updateDTOToEntity(updateRecipeRequest, toBeUpdated);
        return mapper.entityToResponse(recipeRepository.save(updatedRecipe));
    }

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }
}
