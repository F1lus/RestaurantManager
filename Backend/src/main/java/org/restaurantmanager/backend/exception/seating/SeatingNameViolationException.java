package org.restaurantmanager.backend.exception.seating;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class SeatingNameViolationException extends ApplicationException {

    public SeatingNameViolationException() {
        super(ApplicationError.SEATING_NAME_DUPLICATE);
    }
}
