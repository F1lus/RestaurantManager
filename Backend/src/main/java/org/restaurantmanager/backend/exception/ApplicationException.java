package org.restaurantmanager.backend.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.restaurantmanager.backend.util.exception.ApplicationError;

@Getter
@Slf4j
public abstract class ApplicationException extends RuntimeException {

    private final ApplicationError applicationError;

    public ApplicationException(final ApplicationError applicationError) {
        super(applicationError.getMessage());

        log.info("Application Error: {}", applicationError.getMessage());
        this.applicationError = applicationError;
    }
}
