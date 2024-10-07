package org.restaurantmanager.backend.dto.seating;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface SeatingValidation extends ApplicationValidation {
    String PERSON_COUNT_NOT_NEGATIVE = "person_count_not_negative";
}
