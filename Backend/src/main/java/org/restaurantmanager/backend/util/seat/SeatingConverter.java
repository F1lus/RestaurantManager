package org.restaurantmanager.backend.util.seat;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.Seating;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SeatingConverter {

    public static Seating toResponse(final SeatingEntity seatingEntity) {
        return Seating.builder()
                .id(seatingEntity.getId())
                .name(seatingEntity.getName())
                .personCount(seatingEntity.getPersonCount())
                .build();
    }

    public static SeatingEntity toEntity(final CreateSeatingRequest createSeatingRequest) {
        return SeatingEntity.builder()
                .name(createSeatingRequest.getName())
                .personCount(createSeatingRequest.getPersonCount())
                .build();
    }

}
