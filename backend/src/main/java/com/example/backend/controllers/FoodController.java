package com.example.backend.controllers;

import com.example.backend.dtos.FoodDTO;
import com.example.backend.entities.Food;
import com.example.backend.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping
    public List<FoodDTO> findAll() {
        List<Food> foodList = foodService.findAll();
        List<FoodDTO> foodDtoList = foodList.stream().map(FoodDTO::new).toList();
        return foodDtoList;
    }
}
