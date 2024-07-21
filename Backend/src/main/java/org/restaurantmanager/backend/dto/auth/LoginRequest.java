package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public final class LoginRequest {

    @NotNull(message = "The email is required")
    @Email(message = "The email format is invalid")
    private final String email;

    @NotNull(message = "The password is required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@_!%&*?])[A-Za-z\\d#$@_!%&*?]{8,}$",
            message = "The password format is invalid"
    )
    private final String password;

}
