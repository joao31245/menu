package com.jotace.menu.controller;

import com.jotace.menu.domain.food.Food;
import com.jotace.menu.domain.food.FoodRepository;
import com.jotace.menu.domain.food.FoodRequest;
import com.jotace.menu.domain.food.FoodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {
    @Autowired
    FoodRepository repository;
    @GetMapping
    public ResponseEntity<List<FoodResponse>> GetAll() {
        var responseList = repository.findAll().stream().map(FoodResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }
    @PostMapping
    public ResponseEntity<FoodResponse> createFood(@RequestBody FoodRequest request, UriComponentsBuilder uriBuilder) {
        var food = new Food(request);
        repository.save(food);
        var uri = uriBuilder.path("/food/{id}").buildAndExpand(food.getId()).toUri();
        return ResponseEntity.created(uri).body(new FoodResponse(food));
    }

}
