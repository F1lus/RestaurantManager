package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class PasswordConfirmException extends ApplicationException {

    public PasswordConfirmException() {
        super(ApplicationError.PASSWORD_CONFIRM_INVALID);
    }
}
