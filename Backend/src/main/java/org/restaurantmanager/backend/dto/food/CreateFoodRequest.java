package org.restaurantmanager.backend.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Set;

@Data
public final class CreateFoodRequest implements FoodValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String name;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String description;

    @NotNull(message = FIELD_REQUIRED)
    @PositiveOrZero(message = PRICE_NEGATIVE)
    private final Integer price;

    private final Set<String> allergens;

}
