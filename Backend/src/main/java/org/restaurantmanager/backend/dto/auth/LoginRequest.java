package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public final class LoginRequest implements AuthValidation {

    @NotNull(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    private final String email;

    @NotNull(message = PASSWORD_REQUIRED)
    @Pattern(
            regexp = PASSWORD_REGEX,
            message = PASSWORD_FORMAT_INVALID
    )
    private final String password;

}
