package org.restaurantmanager.backend.dto.allergen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ModifyAllergenRequest implements AllergenValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String name;
}
