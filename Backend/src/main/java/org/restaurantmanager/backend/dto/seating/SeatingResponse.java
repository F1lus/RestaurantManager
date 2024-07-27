package org.restaurantmanager.backend.dto.seating;

import lombok.Data;

import java.util.UUID;

@Data
public final class SeatingResponse {

    private final UUID id;

    private final String name;

    private final Integer personCount;
}
