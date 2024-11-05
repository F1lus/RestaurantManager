package org.restaurantmanager.backend.exception.reservation;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ReservationDeleteDeniedException extends ApplicationException {

    public ReservationDeleteDeniedException() {
        super(ApplicationError.RESERVATION_DELETE_DENIED);
    }
}
