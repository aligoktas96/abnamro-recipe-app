package com.assignment.abnamro.unittest;

import com.assignment.abnamro.common.DataProvider;
import com.assignment.abnamro.model.dto.request.CreateRecipeRequest;
import com.assignment.abnamro.model.dto.request.UpdateRecipeRequest;
import com.assignment.abnamro.model.entity.Recipe;
import com.assignment.abnamro.model.mapper.Mapper;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class RecipeUnitTest {

    @Mock
    private RecipeRepository recipeRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Spy
    private DataProvider dataProvider;
    @Spy
    Mapper mapper;

    @Test
    void shouldTriggerCreateMethod(){
        //Arrange
        CreateRecipeRequest sampleCreateRequest = dataProvider.getCreateRequest();
        Recipe sampleRecipe = dataProvider.getRecipe();
        when(mapper.createDTOToEntity(sampleCreateRequest)).thenReturn(sampleRecipe);

        //Act
        recipeService.createRecipe(sampleCreateRequest);

        //Verify
        verify(recipeRepository, times(1)).save(sampleRecipe);

    }

    @Test
    void shouldTriggerUpdateMethod(){
        //Arrange
        UpdateRecipeRequest sampleUpdateRequest = dataProvider.getUpdateRecipeRequest();
        Recipe sampleRecipe = dataProvider.getRecipe();
        when(recipeRepository.findById(sampleRecipe.getId())).thenReturn(Optional.of(sampleRecipe));
        when(mapper.updateDTOToEntity(sampleUpdateRequest,sampleRecipe)).thenReturn(sampleRecipe);

        //Act
        recipeService.updateRecipe(sampleRecipe.getId(),sampleUpdateRequest);

        //Verify
        verify(recipeRepository, times(1)).findById(sampleRecipe.getId());
        verify(recipeRepository, times(1)).save(sampleRecipe);
    }

    @Test
    void shouldTriggerDeleteMethod(){
        //Arrange
        Recipe sampleRecipe = dataProvider.getRecipe();
        doNothing().when(recipeRepository).deleteById(sampleRecipe.getId());

        //Act
        recipeService.deleteRecipe(sampleRecipe.getId());

        //Verify
        verify(recipeRepository, times(1)).deleteById(sampleRecipe.getId());
    }

}
