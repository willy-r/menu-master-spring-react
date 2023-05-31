package com.example.backend.services;

import com.example.backend.entities.Food;
import com.example.backend.repositories.FoodRepository;
import com.example.backend.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;

    public List<Food> findAll() {
        return foodRepository.findAll();
    }

    public Food insert(Food foodObj) {
        return foodRepository.save(foodObj);
    }

    public Food findById(Long id) {
        Optional<Food> foodObj = foodRepository.findById(id);
        return foodObj.orElseThrow(() -> new ObjectNotFoundException("Object with identifier " + id + " not found."));
    }

    public void delete(Long id) {
        findById(id);
        foodRepository.deleteById(id);
    }
}
