package org.restaurantmanager.backend.exception.reservation;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ReservationTimeInvalidException extends ApplicationException {

    public ReservationTimeInvalidException() {
        super(ApplicationError.RESERVATION_TIME_INVALID);
    }
}
