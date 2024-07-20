package org.restaurantmanager.backend.controller.exception;

import lombok.val;
import org.restaurantmanager.backend.exception.ApplicationException;
import org.restaurantmanager.backend.exception.auth.AccessDeniedException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> handleApplicationException(ApplicationException exception) {
        // No logs needed here since the ApplicationException class handles that already
        val errorStatus = exception.getErrorStatus();
        return ResponseEntity.status(errorStatus.getHttpStatus())
                .body(errorStatus.name());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> handleAuthenticationException() {
        val accessDeniedException = new AccessDeniedException();
        return handleApplicationException(accessDeniedException);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        val validationErrors = exception.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return ResponseEntity.badRequest()
                .body(validationErrors);
    }

}
