package org.restaurantmanager.backend.exception.reservation;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class ReservationConflictException extends ApplicationException {

    public ReservationConflictException() {
        super(ApplicationError.RESERVATION_CONFLICT);
    }
}
