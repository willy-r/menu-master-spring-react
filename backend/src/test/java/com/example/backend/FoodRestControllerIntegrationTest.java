package com.example.backend;


import com.example.backend.dtos.food.FoodRequestDTO;
import com.example.backend.entities.Food;
import com.example.backend.repositories.FoodRepository;
import com.example.backend.services.util.JsonUtil;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class FoodRestControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private FoodRepository foodRepository;

    @After
    public void resetDb() {
        foodRepository.deleteAll();
    }

    @Test
    public void givenFoods_whenGetFoods_thenStatus200() throws Exception {
        createTestFood("FoodTest1", "test-image1", 10.0);
        createTestFood("FoodTest2", "test-image2", 10.0);

        mvc.perform(get("/foods").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$", hasSize(equalTo(2))))
            .andExpect(jsonPath("$[0].title", is("FoodTest1")))
            .andExpect(jsonPath("$[1].title", is("FoodTest2")));
    }

    @Test
    public void givenFoodId_whenGetFood_thenStatus200() throws Exception {
        createTestFood("FoodTest", "test-image", 10.0);

        mvc.perform(get("/foods/1").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title", is("FoodTest")));
    }

    @Test
    public void givenNonExistingFoodId_whenGetFood_thenStatus404() throws Exception {
        mvc.perform(get("/foods/1").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenValidInput_whenCreateNewFood_thenCreateFood() throws Exception {
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO("TestFood", "test-image", 10.0);
        mvc.perform(post("/foods").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(foodRequestDTO)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Food> found = foodRepository.findAll();
        assertThat(found).extracting(Food::getId).doesNotContainNull();
        assertThat(found).extracting(Food::getTitle).containsOnly(foodRequestDTO.title());
    }

    @Test
    public void givenFoodId_whenDeleteFood_thenDeleteFood() throws Exception {
        createTestFood("FoodTest", "test-image", 10.0);

        mvc.perform(delete("/foods/1").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNoContent());

        List<Food> found = foodRepository.findAll();
        assertThat(found).hasSize(0);
    }

    @Test
    public void givenNonExistingFoodId_whenDeleteFood_thenStatus404() throws Exception {
        mvc.perform(delete("/foods/1").contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void givenFoodIdWithValidInput_whenUpdateFood_thenUpdateFood() throws Exception {
        createTestFood("FoodTest", "test-image", 10.0);

        FoodRequestDTO foodRequestDTO = new FoodRequestDTO("TestFoodUpdate", "test-image-update", 5.0);
        mvc.perform(put("/foods/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(foodRequestDTO)))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        List<Food> found = foodRepository.findAll();
        assertThat(found).extracting(Food::getTitle).containsOnly(foodRequestDTO.title());
        assertThat(found).extracting(Food::getImage).containsOnly(foodRequestDTO.image());
        assertThat(found).extracting(Food::getPrice).containsOnly(foodRequestDTO.price());
    }

    @Test
    public void givenNonExistingFoodId_whenUpdateFood_thenStatus404() throws Exception {
        FoodRequestDTO foodRequestDTO = new FoodRequestDTO("TestFoodUpdate", "test-image-update", 5.0);
        mvc.perform(put("/foods/1").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(foodRequestDTO)))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    private void createTestFood(String title, String image, double price) {
        Food food = new Food(title, image, price);
        foodRepository.save(food);
    }
}
