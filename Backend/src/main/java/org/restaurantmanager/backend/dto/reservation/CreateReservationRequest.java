package org.restaurantmanager.backend.dto.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public final class CreateReservationRequest implements ReservationValidation {

    @NotNull(message = SEATING_ID_REQUIRED)
    private final UUID seatingId;

    @Null
    private final List<UUID> foodIds;

    @NotNull(message = RESERVATION_START_REQUIRED)
    private final LocalDateTime reservationStart;

    @NotNull(message = RESERVATION_END_REQUIRED)
    private final LocalDateTime reservationEnd;
}
