package org.restaurantmanager.backend.util.food;

import org.restaurantmanager.backend.dto.food.CreateFoodRequest;
import org.restaurantmanager.backend.dto.food.FoodResponse;
import org.restaurantmanager.backend.dto.food.ModifyFoodRequest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

public interface IFoodService {

    List<FoodResponse> getAllFoods();

    @PreAuthorize("hasAuthority('ADMIN')")
    void createFood(final CreateFoodRequest createFoodRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    void modifyFood(final UUID id, final ModifyFoodRequest modifyFoodRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteFood(final UUID id);
}
