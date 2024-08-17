package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.util.reservation.IReservationService;
import org.restaurantmanager.backend.util.reservation.ReservationApi;
import org.restaurantmanager.backend.util.reservation.ReservationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {

    private final IReservationService reservationService;

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

        return ResponseEntity.ok(reservationService.getReservations(reservationFilter));
    }

    @Override
    public ResponseEntity<Void> createReservation(final CreateReservationRequest createReservationRequest) {
        reservationService.createReservation(createReservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> modifyReservation(
            final UUID id,
            final ModifyReservationRequest modifyReservationRequest
    ) {
        reservationService.modifyReservation(modifyReservationRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteReservation(final UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
