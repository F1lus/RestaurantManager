package org.restaurantmanager.backend.util.reservation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.restaurantmanager.backend.datamodel.entity.ReservationEntity;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.util.food.FoodConverter;
import org.restaurantmanager.backend.util.profile.ProfileConverter;
import org.restaurantmanager.backend.util.seat.SeatingConverter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ReservationConverter {

    public static Reservation toResponse(final ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .seats(SeatingConverter.toResponse(reservationEntity.getSeatingEntities()))
                .foods(FoodConverter.toResponse(reservationEntity.getFoods()))
                .generalProfile(ProfileConverter.toResponse(reservationEntity.getReservedBy()))
                .reservationStart(reservationEntity.getReservationStart())
                .reservationEnd(reservationEntity.getReservationEnd())
                .build();
    }
}
