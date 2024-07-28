package org.restaurantmanager.backend.dto.reservation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
public final class ModifyReservationRequest implements ReservationValidation {

    @Null
    private final UUID seatingId;

    @NotNull(message = FOOD_IDS_REQUIRED)
    private final Set<UUID> foodIds;

    @Null
    private final LocalDateTime reservationStart;

    @Null
    private final LocalDateTime reservationEnd;
}
