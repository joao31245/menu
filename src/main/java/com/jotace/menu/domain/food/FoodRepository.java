package com.jotace.menu.domain.food;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
     boolean existsByTitleAndImageAndPrice(String title, String image, Integer price);

     Food findByTitleAndPrice(@NotBlank String title, Integer price);
}
