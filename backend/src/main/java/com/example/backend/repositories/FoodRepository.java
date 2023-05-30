package com.example.backend.repositories;

import com.example.backend.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository  extends JpaRepository<Food, Long> {
}
