package org.restaurantmanager.backend.dto.allergen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class ModifyAllergenRequest implements AllergenValidation {

    @NotNull(message = NAME_REQUIRED)
    @NotBlank(message = NAME_EMPTY)
    private final String name;
}
