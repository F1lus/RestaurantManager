package org.restaurantmanager.backend.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Set;

@Data
public final class ModifyFoodRequest implements FoodValidation {

    @NotBlank(message = FIELD_REQUIRED)
    private final String name;

    @NotBlank(message = FIELD_REQUIRED)
    private final String description;

    @PositiveOrZero(message = PRICE_NEGATIVE)
    private final Integer price;

    private final Set<String> allergens;
}
