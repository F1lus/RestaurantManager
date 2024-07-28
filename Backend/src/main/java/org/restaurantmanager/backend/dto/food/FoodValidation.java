package org.restaurantmanager.backend.dto.food;

public interface FoodValidation {

    String NAME_NOT_NULL = "name_not_null";
    String NAME_NOT_EMPTY = "name_not_empty";

    String DESCRIPTION_NOT_NULL = "description_not_null";
    String DESCRIPTION_NOT_EMPTY = "description_not_empty";

    String PRICE_NOT_NULL = "price_not_null";
    String PRICE_NEGATIVE = "price_negative";

    String ALLERGENS_NOT_NULL = "allergens_not_null";
}
