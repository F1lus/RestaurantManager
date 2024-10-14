package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ProfileNotFoundException extends ApplicationException {

    public ProfileNotFoundException() {
        super(ApplicationError.PROFILE_NOT_FOUND);
    }
}
