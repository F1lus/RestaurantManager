package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.restaurantmanager.backend.util.reservation.IReservationService;
import org.restaurantmanager.backend.util.reservation.ReservationApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ReservationController implements ReservationApi {

    private final IReservationService reservationService;

    @Override
    public ResponseEntity<Iterable<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
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
        reservationService.modifyReservation(id, modifyReservationRequest);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteReservation(final UUID id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
