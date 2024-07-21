package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ProfileConstraintViolationException extends ApplicationException {

    public ProfileConstraintViolationException(ApplicationError applicationError) {
        super(applicationError);
    }
}
