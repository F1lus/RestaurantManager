package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.exception.ErrorStatus;

public class PasswordConfirmException extends ApplicationException {

    public PasswordConfirmException() {
        super(ErrorStatus.PASSWORD_CONFIRM_INVALID);
    }
}
