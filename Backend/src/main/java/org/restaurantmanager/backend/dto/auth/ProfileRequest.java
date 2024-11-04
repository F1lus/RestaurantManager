package org.restaurantmanager.backend.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ProfileRequest implements AuthValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    private final String email;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String fullName;

    @NotNull(message = FIELD_REQUIRED)
    @Pattern(
            regexp = PASSWORD_REGEX,
            message = PASSWORD_FORMAT_INVALID
    )
    private final String password;

    @NotNull(message = FIELD_REQUIRED)
    private final String passwordRepeat;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String phoneNumber;

}
