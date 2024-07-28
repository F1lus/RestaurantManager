package org.restaurantmanager.backend.exception.seating;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class SeatingConstraintViolationException extends ApplicationException {

    public SeatingConstraintViolationException(ApplicationError applicationError) {
        super(applicationError);
    }

}
