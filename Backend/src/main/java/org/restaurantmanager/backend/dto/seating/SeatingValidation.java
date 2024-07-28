package org.restaurantmanager.backend.dto.seating;

public interface SeatingValidation {

    String NAME_REQUIRED = "name_required";
    String NAME_BLANK = "name_blank";

    String PERSON_COUNT_REQUIRED = "person_count_required";
    String PERSON_COUNT_NOT_POSITIVE = "person_count_not_positive";
}
