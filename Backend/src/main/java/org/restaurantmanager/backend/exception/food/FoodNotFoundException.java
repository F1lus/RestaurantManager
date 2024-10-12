package org.restaurantmanager.backend.exception.food;

import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.util.exception.ApplicationError;

public final class FoodNotFoundException extends ApplicationException {

    public FoodNotFoundException() {
        super(ApplicationError.FOOD_ID_INVALID);
    }
}
