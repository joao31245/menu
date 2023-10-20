package com.jotace.menu.core.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class FoodException extends RuntimeException{
    public FoodException(String message) {
        super(message);
    }
}
