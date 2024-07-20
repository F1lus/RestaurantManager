package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public final class RegisterRequest {

    @NotNull(message = "The email is required")
    @Email(message = "The email format is invalid")
    private final String email;

    @NotNull(message = "The name is required")
    @Min(
            value = 3,
            message = "The name must be at least 3 characters long"
    )
    @Max(
            value = 30,
            message = "The name cannot be longer than 30 characters"
    )
    private final String fullName;

    @NotNull(message = "The password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
            message = "The password format is invalid"
    )
    private final String password;

    @NotNull(message = "The password confirmation is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",
            message = "The password confirmation format is invalid"
    )
    private final String confirmPassword;

    private final String phoneNumber;

}
