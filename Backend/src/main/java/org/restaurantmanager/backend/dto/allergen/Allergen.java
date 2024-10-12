package org.restaurantmanager.backend.dto.allergen;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public final class Allergen {

    private final UUID id;
    private final String name;

}
