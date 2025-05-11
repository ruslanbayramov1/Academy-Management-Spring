package org.springframework.academymanagement.handler;


import org.springframework.academymanagement.dto.response.ExceptionResponse;
import org.springframework.academymanagement.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleBaseException(BaseRuntimeException ex) {
        HttpStatus status = ex.getStatus();
        String message = ex.getMessage();

        ExceptionResponse<Void> response = new ExceptionResponse<>(false, status, message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
