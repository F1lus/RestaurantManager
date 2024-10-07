package org.restaurantmanager.backend.dto.food;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface FoodValidation extends ApplicationValidation {
    String PRICE_NEGATIVE = "price_negative";
}
