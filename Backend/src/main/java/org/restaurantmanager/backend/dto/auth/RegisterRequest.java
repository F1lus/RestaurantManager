package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class RegisterRequest implements AuthValidation {

    @NotNull(message = EMAIL_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    private final String email;

    @NotNull(message = NAME_REQUIRED)
    @Size(
            min = 3,
            max = 30,
            message = NAME_LENGTH_INVALID
    )
    private final String fullName;

    @NotNull(message = PASSWORD_REQUIRED)
    @Pattern(
            regexp = PASSWORD_REGEX,
            message = PASSWORD_FORMAT_INVALID
    )
    private final String password;

    @NotNull(message = PASSWORD_CONFIRM_REQUIRED)
    @Pattern(
            regexp = PASSWORD_REGEX,
            message = PASSWORD_CONFIRM_FORMAT_INVALID
    )
    private final String confirmPassword;

    private final String phoneNumber;

}
