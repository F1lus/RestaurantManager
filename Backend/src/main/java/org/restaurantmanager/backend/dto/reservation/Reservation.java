package org.restaurantmanager.backend.dto.reservation;

import lombok.Builder;
import org.restaurantmanager.backend.dto.food.Food;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.seating.Seating;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public final class Reservation {
    private UUID id;
    private Seating seating;
    private GeneralProfile generalProfile;
    private Set<Food> foods;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
}
