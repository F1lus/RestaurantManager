package org.restaurantmanager.backend.dto.auth;

import org.restaurantmanager.backend.dto.ApplicationValidation;

public interface AuthValidation extends ApplicationValidation {
    String EMAIL_FORMAT_INVALID = "email_format_invalid";

    String PASSWORD_FORMAT_INVALID = "password_format_invalid";

    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@_!%&*?])[A-Za-z\\d#$@_!%&*?]{8,}$";
}
