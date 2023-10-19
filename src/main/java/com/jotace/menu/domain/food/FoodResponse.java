package com.jotace.menu.domain.food;

public record FoodResponse(String title, String image, Integer price) {
    public FoodResponse(Food food) {
        this(food.getTitle(), food.getImage(), food.getPrice());
    }
}
