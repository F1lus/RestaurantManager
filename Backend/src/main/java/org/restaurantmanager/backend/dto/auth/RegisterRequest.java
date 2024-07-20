package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull @Email String email,
        @NotNull @NotBlank String fullName,
        @NotNull String password,
        @NotNull String confirmPassword,
        @NotNull @NotBlank String phoneNumber
) {
}
