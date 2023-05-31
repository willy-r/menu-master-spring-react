package com.example.backend.controllers;

import com.example.backend.dtos.food.FoodRequestDTO;
import com.example.backend.dtos.food.FoodResponseDTO;
import com.example.backend.entities.Food;
import com.example.backend.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<FoodResponseDTO> findAll() {
        List<Food> foodList = foodService.findAll();
        return foodList.stream().map(FoodResponseDTO::new).toList();
    }

    @GetMapping("/{id}")
    public FoodResponseDTO findById(@PathVariable Long id) {
        Food foodObj = foodService.findById(id);
        return new FoodResponseDTO(foodObj);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodResponseDTO insert(@RequestBody FoodRequestDTO foodRequestDTO) {
        Food foodObj = Food.fromRequestDTO(foodRequestDTO);
        foodObj = foodService.insert(foodObj);
        return new FoodResponseDTO(foodObj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        foodService.delete(id);
    }
}
