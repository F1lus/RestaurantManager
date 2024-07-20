package org.restaurantmanager.backend.controller.auth.exception;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.exception.auth.AccessDeniedException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { ApplicationException.class })
    public ResponseEntity<String> handleApplicationException(ApplicationException exception) {
        // No logs needed here since the ApplicationException class handles that already
        val errorStatus = exception.getErrorStatus();
        return ResponseEntity.status(errorStatus.getHttpStatus())
                .body(errorStatus.name());
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<String> handleAuthenticationException() {
        val accessDeniedException = new AccessDeniedException();
        return handleApplicationException(accessDeniedException);
    }

}
