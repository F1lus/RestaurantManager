package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ProfileEmailViolationException extends ApplicationException {

    public ProfileEmailViolationException() {
        super(ApplicationError.PROFILE_EMAIL_DUPLICATE);
    }
}
