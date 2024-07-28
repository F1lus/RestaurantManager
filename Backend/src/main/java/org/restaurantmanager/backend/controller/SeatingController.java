package org.restaurantmanager.backend.controller;

import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.ModifySeatingRequest;
import org.restaurantmanager.backend.dto.seating.SeatingResponse;
import org.restaurantmanager.backend.util.seat.ISeatingService;
import org.restaurantmanager.backend.util.seat.SeatingApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class SeatingController implements SeatingApi {

    private final ISeatingService seatingService;

    @Override
    public ResponseEntity<Iterable<SeatingResponse>> getSeats() {
        return ResponseEntity.ok(seatingService.getSeats());
    }

    @Override
    public ResponseEntity<Void> addSeat(final CreateSeatingRequest request) {
        seatingService.addSeat(request);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    public ResponseEntity<Void> modifySeat(final UUID id, final ModifySeatingRequest request) {
        seatingService.modifySeat(id, request);
        return ResponseEntity.ok()
                .build();
    }

    @Override
    public ResponseEntity<Void> deleteSeat(final UUID id) {
        seatingService.deleteSeat(id);
        return ResponseEntity.ok()
                .build();
    }
}
