package org.restaurantmanager.backend.util.reservation;

import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;

import java.util.List;
import java.util.UUID;

public interface IReservationService {

    List<Reservation> getReservations(final ReservationFilter reservationFilter);

    void createReservation(final CreateReservationRequest createReservationRequest);

    void modifyReservation(final ModifyReservationRequest modifyReservationRequest);

    void deleteReservation(final UUID id);
}
