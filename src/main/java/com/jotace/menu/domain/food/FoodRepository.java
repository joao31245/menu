package com.jotace.menu.domain.food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
     boolean existsByTitleAndImageAndPrice(String title, String image, Integer price);
}
