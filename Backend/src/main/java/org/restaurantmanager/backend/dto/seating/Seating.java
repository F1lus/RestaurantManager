package org.restaurantmanager.backend.dto.seating;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public final class Seating {

    private final UUID id;

    private final String name;

    private final Integer personCount;
}
