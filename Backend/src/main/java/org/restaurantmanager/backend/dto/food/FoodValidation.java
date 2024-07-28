package org.restaurantmanager.backend.dto.food;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface FoodValidation extends ApplicationValidation {

    String DESCRIPTION_NOT_NULL = "description_null";
    String DESCRIPTION_EMPTY = "description_empty";

    String PRICE_NOT_NULL = "price_not_null";
    String PRICE_NEGATIVE = "price_negative";

    String ALLERGENS_NULL = "allergens_null";
}
