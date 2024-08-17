package org.restaurantmanager.backend.dto.food;

import lombok.Builder;
import lombok.Data;
import org.restaurantmanager.backend.dto.allergen.Allergen;

import java.util.Set;
import java.util.UUID;

@Builder
@Data
public final class Food {
    private final UUID id;
    private final String name;
    private final String description;
    private final Integer price;
    private final Set<Allergen> allergens;

}
