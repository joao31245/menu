package com.jotace.menu.domain.food;

import jakarta.validation.constraints.NotBlank;

public record FoodRequest(
        @NotBlank
        String title,
        @NotBlank
        String image,
        Integer price) {

}
