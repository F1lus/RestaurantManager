package org.restaurantmanager.backend.util.seat;

import org.restaurantmanager.backend.datamodel.entity.SeatingEntity;
import org.restaurantmanager.backend.dto.seating.CreateSeatingRequest;
import org.restaurantmanager.backend.dto.seating.ModifySeatingRequest;
import org.restaurantmanager.backend.dto.seating.Seating;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

public interface ISeatingService {

    List<Seating> getSeats();

    List<SeatingEntity> getSeatsByIds(final List<UUID> ids);

    @PreAuthorize("hasAuthority('ADMIN')")
    void addSeat(final CreateSeatingRequest createSeatingRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    void modifySeat(final UUID id, final ModifySeatingRequest modifySeatingRequest);

    @PreAuthorize("hasAuthority('ADMIN')")
    void deleteSeat(final UUID id);
}
