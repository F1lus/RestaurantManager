package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class LoginRequest implements AuthValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    private final String email;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String password;

}
