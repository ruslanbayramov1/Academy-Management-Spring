package org.springframework.academymanagement.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseRuntimeException {
    // default status code
    public NotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }

    // custom status code
    public NotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
