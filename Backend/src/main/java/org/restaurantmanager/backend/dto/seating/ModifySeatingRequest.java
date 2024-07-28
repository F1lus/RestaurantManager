package org.restaurantmanager.backend.dto.seating;

import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public final class ModifySeatingRequest implements SeatingValidation {

    @Null
    private final String name;

    @Null
    @Positive(message = PERSON_COUNT_NOT_POSITIVE)
    private final Integer personCount;
}
