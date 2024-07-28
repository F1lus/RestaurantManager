package org.restaurantmanager.backend.util.seat;

import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.SeatingResponse;

public final class SeatingConverter {

    private SeatingConverter() {
    }

    public static SeatingResponse toSeatingRequest(final SeatingEntity seatingEntity) {
        return new SeatingResponse(
                seatingEntity.getId(),
                seatingEntity.getName(),
                seatingEntity.getPersonCount()
        );
    }

    public static SeatingEntity toEntity(final CreateSeatingRequest createSeatingRequest) {
        return SeatingEntity.builder()
                .name(createSeatingRequest.getName())
                .personCount(createSeatingRequest.getPersonCount())
                .build();
    }

}
