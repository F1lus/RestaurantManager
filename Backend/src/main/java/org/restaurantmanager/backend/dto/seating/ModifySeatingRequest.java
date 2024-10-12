package org.restaurantmanager.backend.dto.seating;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ModifySeatingRequest implements SeatingValidation {

    @NotBlank(message = FIELD_REQUIRED)
    private final String name;

    @Positive(message = PERSON_COUNT_NOT_NEGATIVE)
    private final Integer personCount;
}
