package org.restaurantmanager.backend.dto.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.Set;

@Data
public final class CreateFoodRequest implements FoodValidation {

    @NotNull(message = NAME_NOT_NULL)
    @NotBlank(message = NAME_NOT_EMPTY)
    private final String name;

    @NotNull(message = DESCRIPTION_NOT_NULL)
    @NotBlank(message = DESCRIPTION_NOT_EMPTY)
    private final String description;

    @NotNull(message = PRICE_NOT_NULL)
    @PositiveOrZero(message = PRICE_NEGATIVE)
    private final Integer price;

    @NotNull(message = ALLERGENS_NOT_NULL)
    private final Set<String> allergens;

}
