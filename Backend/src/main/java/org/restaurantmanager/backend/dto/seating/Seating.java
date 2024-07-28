package org.restaurantmanager.backend.dto.seating;

import lombok.Data;

import java.util.UUID;

@Data
public final class Seating {

    private final UUID id;

    private final String name;

    private final Integer personCount;
}
