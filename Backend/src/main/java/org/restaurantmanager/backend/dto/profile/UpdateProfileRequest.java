package org.restaurantmanager.backend.dto.profile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.restaurantmanager.backend.dto.auth.AuthValidation;

@RequiredArgsConstructor
@Getter
public class UpdateProfileRequest implements AuthValidation {

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    @Email(message = EMAIL_FORMAT_INVALID)
    private final String email;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String fullName;


    private final String password;

    private final String passwordRepeat;

    @NotNull(message = FIELD_REQUIRED)
    @NotBlank(message = FIELD_REQUIRED)
    private final String phoneNumber;

}
