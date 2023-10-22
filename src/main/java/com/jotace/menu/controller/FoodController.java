package com.jotace.menu.controller;

import com.jotace.menu.domain.food.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "food", produces = ("application/json"))
@Tag(name = "menu")
public class FoodController {
    /** Importing this code and */
    @Autowired
    FoodRepository repository;
    @Autowired
    FoodMethodsValidations validations;

    @GetMapping
    @Operation(summary = "Get a list of all foods in the database", method = "GET")

    public ResponseEntity<List<FoodResponse>> GetAll() {
        var responseList = repository.findAll().stream().map(FoodResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<FoodResponse> createFood(@RequestBody FoodRequest request, UriComponentsBuilder uriBuilder) {
        var food = new Food(request);
        validations.validations(request);
        repository.save(food);
        var uri = uriBuilder.path("/food/{id}").buildAndExpand(food.getId()).toUri();
        return ResponseEntity.created(uri).body(new FoodResponse(food));
    }

}
