package org.restaurantmanager.backend.dto.seating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public final class CreateSeatingRequest implements SeatingValidation {

    @NotNull(message = NAME_REQUIRED)
    @NotBlank(message = NAME_EMPTY)
    private final String name;

    @NotNull(message = PERSON_COUNT_REQUIRED)
    @Positive(message = PERSON_COUNT_NOT_POSITIVE)
    private final Integer personCount;
}
