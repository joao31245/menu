package com.jotace.menu.core.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> error404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<FieldValidationError>> error400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(FieldValidationError::new).toList());

    }

    @ExceptionHandler(FoodException.class)
    public ResponseEntity<String> equalFood(FoodException ex) {
        return ResponseEntity.badRequest().body("Error: " + ex);
    }

    private record FieldValidationError(String field, String message) {
        public FieldValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
