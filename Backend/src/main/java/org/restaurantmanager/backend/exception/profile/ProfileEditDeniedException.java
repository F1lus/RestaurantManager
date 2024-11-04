package org.restaurantmanager.backend.exception.profile;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class ProfileEditDeniedException extends ApplicationException {

    public ProfileEditDeniedException() {
        super(ApplicationError.PROFILE_EDIT_DENIED);
    }
}
