package org.restaurantmanager.backend.util.food;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.dto.food.Food;
import org.restaurantmanager.backend.util.allergen.AllergenConverter;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FoodConverter {

    public static Food toResponse(FoodEntity foodEntity) {
        return Food.builder()
                .id(foodEntity.getId())
                .name(foodEntity.getName())
                .price(foodEntity.getPrice())
                .description(foodEntity.getDescription())
                .allergens(AllergenConverter.toResponse(foodEntity.getAllergens()))
                .build();
    }

    public static Set<Food> toResponse(Collection<FoodEntity> foodEntities) {
        return foodEntities.stream()
                .map(FoodConverter::toResponse)
                .collect(Collectors.toSet());
    }
}
