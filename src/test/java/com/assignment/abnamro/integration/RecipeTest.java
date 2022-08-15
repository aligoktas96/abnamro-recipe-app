package com.assignment.abnamro.integration;

import com.assignment.abnamro.TestInitializer;
import com.assignment.abnamro.common.DataProvider;
import com.assignment.abnamro.model.dto.response.RecipeResponse;
import com.assignment.abnamro.repository.RecipeRepository;
import com.assignment.abnamro.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = TestInitializer.class)
public class RecipeTest {

    private static final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private DataProvider dataProvider;
    @Autowired
    private RecipeService service;
    @Autowired
    private MockMvc mockMvc;

    @Mock
    RecipeRepository recipeRepository;

    @SneakyThrows
    @Test
    void itShouldReturnCreatedRecipe() {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/recipes/create")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(dataProvider.getCreateRequest()))
                )
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Nachos")))
                .andExpect(jsonPath("$.isVegetarian", is(false)))
                .andReturn();
    }

    @SneakyThrows
    @Test
    void itShouldReturnUpdatedRecipeBasedOnId() {

        RecipeResponse recipeResponse = dataProvider.getRecipeResponse();
        when(recipeRepository.findById(2L)).thenReturn(Optional.of(dataProvider.getRecipe()));
        when(service.updateRecipe(2L, dataProvider.getUpdateRecipeRequest())).thenReturn(recipeResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .patch("/recipes/update/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(dataProvider.getUpdateRecipeRequest()))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", is("Nachos Updated")))
                .andExpect(jsonPath("$.isVegetarian", is(false)))
                .andReturn();
    }

    @SneakyThrows
    @Test
    void itShouldReturnRecipe() {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/recipes/")
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }


    @SneakyThrows
    @Test
    void it_should_delete_recipe() {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/recipe/delete/2")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(dataProvider.getUpdateRecipeRequest()))
                )
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn();
    }
}
