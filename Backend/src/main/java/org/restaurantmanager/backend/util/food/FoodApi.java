package org.restaurantmanager.backend.util.food;

import jakarta.validation.Valid;
import org.restaurantmanager.backend.dto.food.CreateFoodRequest;
import org.restaurantmanager.backend.dto.food.FoodResponse;
import org.restaurantmanager.backend.dto.food.ModifyFoodRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.restaurantmanager.backend.util.food.FoodApi.FOOD_PATH;

@RequestMapping(FOOD_PATH)
public interface FoodApi {

    String FOOD_PATH = "/api/food";

    @GetMapping
    ResponseEntity<Iterable<FoodResponse>> getFoods();

    @PostMapping
    ResponseEntity<Void> createFood(@Valid @RequestBody final CreateFoodRequest request);

    @PutMapping("{id}")
    ResponseEntity<Void> updateFood(
            @PathVariable("id") final UUID id,
            @Valid @RequestBody final ModifyFoodRequest request
    );

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteFood(@PathVariable("id") final UUID id);
}
