package org.restaurantmanager.backend.dto.reservation;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public final class ModifyReservationRequest implements ReservationValidation {

    @NotNull(message = FIELD_REQUIRED)
    private final UUID seatingId;

    private final List<UUID> foodIds;

    @NotNull(message = FIELD_REQUIRED)
    private final LocalDateTime reservationStart;

    @NotNull(message = FIELD_REQUIRED)
    private final LocalDateTime reservationEnd;

}
