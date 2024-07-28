package org.restaurantmanager.backend.dto.auth;

public interface AuthValidation {

    String EMAIL_REQUIRED = "email_required";
    String EMAIL_FORMAT_INVALID = "email_format_invalid";

    String PASSWORD_REQUIRED = "password_required";
    String PASSWORD_FORMAT_INVALID = "password_format_invalid";

    String PASSWORD_CONFIRM_REQUIRED = "password_confirm_required";
    String PASSWORD_CONFIRM_FORMAT_INVALID = "password_confirm_format_invalid";

    String NAME_REQUIRED = "name_required";
    String NAME_LENGTH_INVALID = "name_length_invalid";

    String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@_!%&*?])[A-Za-z\\d#$@_!%&*?]{8,}$";

}
