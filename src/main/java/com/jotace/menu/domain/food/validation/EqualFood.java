package com.jotace.menu.domain.food.validation;

import com.jotace.menu.core.exceptions.FoodException;
import com.jotace.menu.domain.food.FoodRepository;
import com.jotace.menu.domain.food.FoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EqualFood implements FoodValidation{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public void validate(FoodRequest foodRequest) {
        if(foodRepository.existsByTitleAndImageAndPrice(foodRequest.title(),foodRequest.image(),foodRequest.price())) {
            throw new FoodException("Already exists a food with the same attributes");
        }
    }
}
