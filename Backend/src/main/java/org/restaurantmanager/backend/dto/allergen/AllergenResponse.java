package org.restaurantmanager.backend.dto.allergen;

import lombok.Data;

import java.util.UUID;

@Data
public final class AllergenResponse {

    private final UUID id;
    private final String name;

}
