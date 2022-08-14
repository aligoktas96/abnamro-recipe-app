package com.assignment.abnamro.model.mapper;

import com.assignment.abnamro.model.dto.request.CreateRecipeRequest;
import com.assignment.abnamro.model.dto.request.GetRecipeRequest;
import com.assignment.abnamro.model.dto.request.UpdateRecipeRequest;
import com.assignment.abnamro.model.dto.response.RecipeResponse;
import com.assignment.abnamro.model.entity.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public Recipe updateDTOToEntity(UpdateRecipeRequest updateRequest, Recipe toBeUpdated) {
        ObjectReader objectReader = objectMapper.readerForUpdating(toBeUpdated);
        return objectReader.readValue(objectMapper.writeValueAsString(updateRequest));
    }

    public RecipeResponse entityToResponse(Recipe recipe) {
        return objectMapper.convertValue(recipe, RecipeResponse.class);

    }

    public Recipe createDTOToEntity(CreateRecipeRequest createRequest) {
        return objectMapper.convertValue(createRequest, Recipe.class);
    }

    public Recipe getDTOToEntity(GetRecipeRequest getRequest) {
        return objectMapper.convertValue(getRequest, Recipe.class);
    }
}

