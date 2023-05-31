package com.example.backend.entities;

import com.example.backend.dtos.food.FoodRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "foods")
@Entity(name = "foods")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String image;
    private double price;

    public Food(String title, String image, double price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public static Food fromRequestDTO(FoodRequestDTO foodRequestDTO) {
        return new Food(foodRequestDTO.title(), foodRequestDTO.image(), foodRequestDTO.price());
    }

    public static void updateData(Food foodEntity, FoodRequestDTO foodRequestDTO) {
        foodEntity.setTitle(foodRequestDTO.title());
        foodEntity.setImage(foodRequestDTO.image());
        foodEntity.setPrice(foodRequestDTO.price());
    }
}
