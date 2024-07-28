package org.restaurantmanager.backend.util.reservation;

import jakarta.validation.Valid;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.restaurantmanager.backend.util.reservation.ReservationApi.RESERVATION_PATH;

@RequestMapping(RESERVATION_PATH)
public interface ReservationApi {

    String RESERVATION_PATH = "/api/reservation";

    @GetMapping
    ResponseEntity<Iterable<Reservation>> getReservation(
            @RequestParam(required = false, name = "reservationId") final UUID reservationId,
            @RequestParam(required = false, name = "profileId") final UUID profileId,
            @RequestParam(required = false, name = "seatingId") final UUID seatingId,
            @RequestParam(required = false, name = "foodIds") final Set<UUID> foodIds,
            @RequestParam(required = false, name = "reservationStart") final LocalDateTime reservationStart,
            @RequestParam(required = false, name = "reservationEnd") final LocalDateTime reservationEnd
    );

    @PostMapping
    ResponseEntity<Void> createReservation(
            @Valid @RequestBody final CreateReservationRequest createReservationRequest
    );

    @PutMapping("{id}")
    ResponseEntity<Void> modifyReservation(
            @PathVariable("id") final UUID id,
            @Valid @RequestBody final ModifyReservationRequest modifyReservationRequest
    );

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteReservation(@PathVariable("id") final UUID id);
}
