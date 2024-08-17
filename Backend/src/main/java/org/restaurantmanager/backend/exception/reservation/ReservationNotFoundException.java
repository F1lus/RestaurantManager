package org.restaurantmanager.backend.exception.reservation;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ReservationNotFoundException extends ApplicationException {

    public ReservationNotFoundException() {
        super(ApplicationError.RESERVATION_NOT_FOUND);
    }
}
