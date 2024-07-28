package org.restaurantmanager.backend.util.food;

import lombok.val;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.dto.food.FoodResponse;
import org.restaurantmanager.backend.util.allergen.AllergenConverter;

import java.util.stream.Collectors;

public final class FoodConverter {

    private FoodConverter() {
    }

    public static FoodResponse toFoodResponse(FoodEntity foodEntity) {
        val allergenResponses = foodEntity.getAllergens()
                .stream()
                .map(AllergenConverter::toAllergenResponse)
                .collect(Collectors.toSet());

        return new FoodResponse(
                foodEntity.getId(),
                foodEntity.getName(),
                foodEntity.getDescription(),
                foodEntity.getPrice(),
                allergenResponses
        );
    }
}
