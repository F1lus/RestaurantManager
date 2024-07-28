package org.restaurantmanager.backend.dto.seating;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface SeatingValidation extends ApplicationValidation {

    String PERSON_COUNT_REQUIRED = "person_count_required";
    String PERSON_COUNT_NOT_POSITIVE = "person_count_not_positive";
}
