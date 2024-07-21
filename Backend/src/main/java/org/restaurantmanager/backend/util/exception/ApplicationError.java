package org.restaurantmanager.backend.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ApplicationError {

    // region General errors
    INCORRECT_CREDENTIALS("The credentials were incorrect", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("Access denied", HttpStatus.UNAUTHORIZED),
    PASSWORD_CONFIRM_INVALID("The passwords are not the same", HttpStatus.BAD_REQUEST),
    // endregion

    // region Profile errors
    PROFILE_EMAIL_DUPLICATE("The email constraint is violated", HttpStatus.CONFLICT),
    PROFILE_PHONE_NUMBER_DUPLICATE("The phone number constraint is violated", HttpStatus.CONFLICT);
    // endregion

    private final String message;
    private final HttpStatus httpStatus;
}
