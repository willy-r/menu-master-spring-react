package com.example.backend.dtos;

import com.example.backend.entities.Food;

public record FoodDTO(Long id, String title, String image, double price) {
    public FoodDTO(Food food) {
        this(food.getId(), food.getTitle(), food.getImage(), food.getPrice());
    }
}
