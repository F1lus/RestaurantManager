package org.restaurantmanager.backend.dto.seating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class CreateSeatingRequest implements SeatingValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String name;

    @NotNull(message = FIELD_REQUIRED)
    @Positive(message = PERSON_COUNT_NOT_NEGATIVE)
    private final Integer personCount;
}
