package com.jotace.menu.controller;

import com.jotace.menu.domain.food.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


    @Operation(summary = "Get a list of all foods in the database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worked!"),
            @ApiResponse(responseCode = "401", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable")
    })
    @GetMapping()
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<FoodResponse>> GetAll() {
        var responseList = repository.findAll().stream().map(FoodResponse::new).toList();
        return ResponseEntity.ok(responseList);
    }
    @Operation(summary = "Register a new food in the database", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Food registered"),
            @ApiResponse(responseCode = "401", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Resource not found")
    })
    @PostMapping
    @Transactional
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<FoodResponse> createFood(@RequestBody FoodRequest request, UriComponentsBuilder uriBuilder) {
        var food = new Food(request);
        validations.validations(request);
        repository.save(food);
        var uri = uriBuilder.path("/food/{id}").buildAndExpand(food.getId()).toUri();
        return ResponseEntity.created(uri).body(new FoodResponse(food));
    }
   @Operation(summary = "Delete a food that exists in the database.")
   @DeleteMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Food deleted")
    })
    public ResponseEntity<String> deleteFood(@RequestBody DeleteRequest request) {
        try {
            var food = repository.findByTitleAndPrice(request.title(), request.price());
            repository.deleteById(food.getId());
            return ResponseEntity.noContent().build();
        } catch (RuntimeException ex) {
            throw new EntityNotFoundException();
        }

    }

}
