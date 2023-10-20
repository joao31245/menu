package com.jotace.menu.domain.food;

import com.jotace.menu.domain.food.validation.FoodValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodMethodsValidations {
   @Autowired
   List<FoodValidation> validationList;

   public void validations(FoodRequest foodRequest) {

       validationList.forEach(validation -> validation.validate(foodRequest));

   }
}
