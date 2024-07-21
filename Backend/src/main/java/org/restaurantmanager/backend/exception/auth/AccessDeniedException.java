package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class AccessDeniedException extends ApplicationException {

    public AccessDeniedException() {
        super(ApplicationError.ACCESS_DENIED);
    }
}
