package org.restaurantmanager.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorStatus {
    INCORRECT_CREDENTIALS("The credentials were incorrect", HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("Access denied", HttpStatus.UNAUTHORIZED),
    PASSWORD_CONFIRM_INVALID("The passwords are not the same", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}
