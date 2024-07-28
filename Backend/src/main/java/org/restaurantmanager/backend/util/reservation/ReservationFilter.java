package org.restaurantmanager.backend.util.reservation;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
public final class ReservationFilter {
    private final UUID reservationId;
    private final UUID profileId;
    private final UUID seatingId;
    private final Set<UUID> foodIds;
    private final LocalDateTime reservationStart;
    private final LocalDateTime reservationEnd;
}
