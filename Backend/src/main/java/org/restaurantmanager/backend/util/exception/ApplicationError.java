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
    PASSWORD_FORMAT_INVALID("The password format is invalid", HttpStatus.BAD_REQUEST),
    // endregion

    // region Profile errors
    PROFILE_EMAIL_DUPLICATE("The profile email constraint is violated", HttpStatus.CONFLICT),
    PROFILE_PHONE_NUMBER_DUPLICATE("The profile phone number constraint is violated", HttpStatus.CONFLICT),
    PROFILE_NOT_FOUND("The profile does not exist", HttpStatus.NOT_FOUND),
    PROFILE_EDIT_DENIED(
            "The profile edit denied because the current user is not the owner of the profile",
            HttpStatus.BAD_REQUEST
    ),
    // endregion

    // region Seating errors
    SEATING_NAME_DUPLICATE("The seating name constraint is violated", HttpStatus.CONFLICT),
    SEATING_ID_INVALID("The seating id is invalid", HttpStatus.NOT_FOUND),
    //endregion

    // region Food errors
    FOOD_ID_INVALID("The food id is invalid", HttpStatus.NOT_FOUND),
    // endregion

    // region Allergen errors
    ALLERGEN_NAME_DUPLICATE("The allergen name constraint is violated", HttpStatus.CONFLICT),
    // endregion

    // region Reservation errors
    RESERVATION_TIME_INVALID("The reservation time is invalid", HttpStatus.BAD_REQUEST),
    RESERVATION_CONFLICT("The reservation is conflicting with another", HttpStatus.CONFLICT),
    RESERVATION_DELETE_DENIED("The reservation delete was denied", HttpStatus.BAD_REQUEST),
    RESERVATION_NOT_FOUND("The reservation could not be found", HttpStatus.NOT_FOUND);
    // endregion

    private final String message;
    private final HttpStatus httpStatus;
}
