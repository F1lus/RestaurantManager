package org.restaurantmanager.backend.dto.allergen;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class ModifyAllergenRequest implements AllergenValidation {

    @NotNull(message = NAME_NOT_NULL)
    @NotBlank(message = NAME_NOT_BLANK)
    private final String name;
}
