package org.restaurantmanager.backend.util.reservation;

import org.restaurantmanager.backend.datamodel.entity.FoodEntity;
import org.restaurantmanager.backend.dto.reservation.CreateReservationRequest;
import org.restaurantmanager.backend.dto.reservation.ModifyReservationRequest;
import org.restaurantmanager.backend.dto.reservation.Reservation;

import java.util.List;
import java.util.UUID;

public interface IReservationService {

    List<Reservation> getReservations();

    void createReservation(final CreateReservationRequest createReservationRequest);

    void modifyReservation(final UUID id, final ModifyReservationRequest modifyReservationRequest);

    void deleteReservation(final UUID id);

    void handleFoodDeletion(final FoodEntity foodEntity);
}
