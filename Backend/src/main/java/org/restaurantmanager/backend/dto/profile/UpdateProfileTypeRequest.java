package org.restaurantmanager.backend.dto.profile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.restaurantmanager.backend.dto.ApplicationValidation;

@Data
public final class UpdateProfileTypeRequest implements ApplicationValidation {

    @NotNull(message = FIELD_REQUIRED)
    private String profileType;

}
