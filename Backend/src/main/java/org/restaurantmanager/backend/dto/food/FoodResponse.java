package org.restaurantmanager.backend.dto.food;

import lombok.Data;
import org.restaurantmanager.backend.dto.allergen.AllergenResponse;

import java.util.Set;
import java.util.UUID;

@Data
public final class FoodResponse {

    private final UUID id;
    private final String name;
    private final String description;
    private final Integer price;
    private final Set<AllergenResponse> allergens;

}
