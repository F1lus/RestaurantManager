package org.restaurantmanager.backend.exception.auth;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.exception.ErrorStatus;

public class AccessDeniedException extends ApplicationException {

    public AccessDeniedException() {
        super(ErrorStatus.ACCESS_DENIED);
    }
}
