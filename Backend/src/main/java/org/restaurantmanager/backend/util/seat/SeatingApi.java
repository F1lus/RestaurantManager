package org.restaurantmanager.backend.util.seat;

import jakarta.validation.Valid;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.ModifySeatingRequest;
import org.restaurantmanager.backend.dto.seating.SeatingResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static org.restaurantmanager.backend.util.seat.SeatingApi.SEATING_PATH;

@RequestMapping(SEATING_PATH)
public interface SeatingApi {

    String SEATING_PATH = "/api/seating";

    @GetMapping
    ResponseEntity<Iterable<SeatingResponse>> getSeats();

    @PostMapping
    ResponseEntity<Void> addSeat(@Valid @RequestBody final CreateSeatingRequest request);

    @PutMapping("{id}")
    ResponseEntity<Void> modifySeat(
            @PathVariable final UUID id,
            @Valid @RequestBody final ModifySeatingRequest request
    );

    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteSeat(@PathVariable("id") final UUID id);
}
