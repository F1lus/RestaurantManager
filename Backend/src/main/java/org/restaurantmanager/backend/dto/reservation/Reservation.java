package org.restaurantmanager.backend.dto.reservation;

import lombok.Builder;
import lombok.Getter;
import org.restaurantmanager.backend.dto.food.Food;
import org.restaurantmanager.backend.dto.profile.GeneralProfile;
import org.restaurantmanager.backend.dto.seating.Seating;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
public final class Reservation {
    private UUID id;
    private List<Seating> seats;
    private GeneralProfile generalProfile;
    private Set<Food> foods;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
}
