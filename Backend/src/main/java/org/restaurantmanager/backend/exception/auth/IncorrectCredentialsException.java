package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.exception.ErrorStatus;

public class IncorrectCredentialsException extends ApplicationException {

    public IncorrectCredentialsException() {
        super(ErrorStatus.INCORRECT_CREDENTIALS);
    }

}
