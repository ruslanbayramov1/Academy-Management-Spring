package org.springframework.academymanagement.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseRuntimeException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
    private final boolean success;

    public BaseRuntimeException(String message, HttpStatus statusCode) {
        super(message);
        this.status = statusCode;
        this.message = message;
        this.success = false;
    }
}
