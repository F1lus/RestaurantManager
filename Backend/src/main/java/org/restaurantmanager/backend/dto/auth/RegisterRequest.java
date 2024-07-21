package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public final class RegisterRequest {

    @NotNull(message = "The email is required")
    @Email(message = "The email format is invalid")
    private final String email;

    @NotNull(message = "The name is required")
    @Size(
            min = 3,
            max = 30,
            message = "The length of the name must be between 3 and 30 characters"
    )
    private final String fullName;

    @NotNull(message = "The password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@_!%&*?])[A-Za-z\\d#$@_!%&*?]{8,}$",
            message = "The password format is invalid"
    )
    private final String password;

    @NotNull(message = "The password confirmation is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@_!%&*?])[A-Za-z\\d#$@_!%&*?]{8,}$",
            message = "The password confirmation format is invalid"
    )
    private final String confirmPassword;

    private final String phoneNumber;

}
