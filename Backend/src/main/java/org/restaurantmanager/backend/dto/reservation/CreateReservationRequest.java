package org.restaurantmanager.backend.dto.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public final class CreateReservationRequest implements ReservationValidation {

    @NotNull(message = SEATING_ID_REQUIRED)
    private final UUID seatingId;

    @NotNull(message = FOOD_IDS_REQUIRED)
    private final Set<UUID> foodIds;

    @NotNull(message = RESERVATION_START_REQUIRED)
    private final LocalDateTime reservationStart;

    @NotNull(message = RESERVATION_END_REQUIRED)
    private final LocalDateTime reservationEnd;
}
