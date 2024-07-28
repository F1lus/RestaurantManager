package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.util.reservation.ReservationApi;
import org.restaurantmanager.backend.util.reservation.ReservationFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {


    @Override
    public ResponseEntity<Iterable<Reservation>> getReservation(
            final UUID reservationId,
            final UUID profileId,
            final UUID seatingId,
            final Set<UUID> foodIds,
            final LocalDateTime reservationStart,
            final LocalDateTime reservationEnd
    ) {
        val reservationFilter = ReservationFilter.builder()
                .reservationId(reservationId)
                .profileId(profileId)
                .seatingId(seatingId)
                .foodIds(foodIds)
                .reservationStart(reservationStart)
                .reservationEnd(reservationEnd)
                .build();

        return null;
    }

    @Override
    public ResponseEntity<Void> createReservation(final CreateReservationRequest createReservationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> modifyReservation(
            final UUID id,
            final ModifyReservationRequest modifyReservationRequest
    ) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteReservation(final UUID id) {
        return null;
    }
}
