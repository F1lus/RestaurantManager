package org.restaurantmanager.backend.exception.allergen;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class AllergenConstraintViolationException extends ApplicationException {

    public AllergenConstraintViolationException(final ApplicationError applicationError) {
        super(applicationError);
    }
}
