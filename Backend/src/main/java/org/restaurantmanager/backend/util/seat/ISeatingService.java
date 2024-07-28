package org.restaurantmanager.backend.util.seat;

import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.ModifySeatingRequest;
import org.restaurantmanager.backend.dto.seating.SeatingResponse;

import java.util.List;
import java.util.UUID;

public interface ISeatingService {

    List<SeatingResponse> getSeats();

    void addSeat(final CreateSeatingRequest createSeatingRequest);

    void modifySeat(final UUID id, final ModifySeatingRequest modifySeatingRequest);

    void deleteSeat(final UUID id);
}
