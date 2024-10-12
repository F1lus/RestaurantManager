package org.restaurantmanager.backend.dto.reservation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
public final class ReservationQuery {
    private final UUID reservationId;
    private final UUID profileId;
    private final UUID seatingId;
    private final Set<UUID> foodIds;
    private final LocalDateTime reservationStart;
    private final LocalDateTime reservationEnd;
}
