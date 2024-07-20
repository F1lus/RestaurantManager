package org.restaurantmanager.backend.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public abstract class ApplicationException extends RuntimeException {

    private final ErrorStatus errorStatus;

    public ApplicationException(final ErrorStatus errorStatus) {
        super(errorStatus.getMessage());

        log.info("Application Error: {}", errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }
}
