package org.restaurantmanager.backend.exception.allergen;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public class AllergenConstraintViolationException extends ApplicationException {

    public AllergenConstraintViolationException(ApplicationError applicationError) {
        super(applicationError);
    }
}
