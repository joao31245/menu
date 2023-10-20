package com.jotace.menu.domain.food.validation;

import com.jotace.menu.domain.food.FoodRequest;

public interface FoodValidation {
    void validate(FoodRequest foodRequest);
}
