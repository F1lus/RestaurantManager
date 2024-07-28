package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.food.CreateFoodRequest;
import org.restaurantmanager.backend.dto.food.FoodResponse;
import org.restaurantmanager.backend.dto.food.ModifyFoodRequest;
import org.restaurantmanager.backend.util.food.FoodApi;
import org.restaurantmanager.backend.util.food.IFoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class FoodController implements FoodApi {

    private final IFoodService foodService;

    @Override
    public ResponseEntity<Iterable<FoodResponse>> getFoods() {
        return ResponseEntity.ok(foodService.getAllFoods());
    }

    @Override
    public ResponseEntity<Void> createFood(final CreateFoodRequest request) {
        foodService.createFood(request);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    public ResponseEntity<Void> updateFood(final UUID id, final ModifyFoodRequest request) {
        foodService.modifyFood(id, request);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    public ResponseEntity<Void> deleteFood(final UUID id) {
        foodService.deleteFood(id);
        return ResponseEntity.ok()
                .build();
    }
}
