package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class PasswordFormatInvalidException extends ApplicationException {

    public PasswordFormatInvalidException() {
        super(ApplicationError.PASSWORD_FORMAT_INVALID);
    }
}
