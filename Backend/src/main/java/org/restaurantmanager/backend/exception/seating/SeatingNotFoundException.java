package org.restaurantmanager.backend.exception.seating;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class SeatingNotFoundException extends ApplicationException {

    public SeatingNotFoundException() {
        super(ApplicationError.SEATING_ID_INVALID);
    }
}
