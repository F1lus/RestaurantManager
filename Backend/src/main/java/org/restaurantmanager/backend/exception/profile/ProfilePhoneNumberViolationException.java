package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class ProfilePhoneNumberViolationException extends ApplicationException {

    public ProfilePhoneNumberViolationException() {
        super(ApplicationError.PROFILE_PHONE_NUMBER_DUPLICATE);
    }
}
