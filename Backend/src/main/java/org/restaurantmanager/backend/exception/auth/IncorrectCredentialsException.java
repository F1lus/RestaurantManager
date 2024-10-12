package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class IncorrectCredentialsException extends ApplicationException {

    public IncorrectCredentialsException() {
        super(ApplicationError.INCORRECT_CREDENTIALS);
    }

}
