package com.example.backend.services;

import com.example.backend.entities.Food;
import com.example.backend.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> findAll() {
        return foodRepository.findAll();
    }
}
