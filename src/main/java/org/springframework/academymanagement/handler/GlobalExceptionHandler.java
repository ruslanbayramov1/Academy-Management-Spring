package org.springframework.academymanagement.handler;


import org.springframework.academymanagement.dto.response.ExceptionResponse;
import org.springframework.academymanagement.exception.BaseRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<ExceptionResponse<Void>> handleBaseException(BaseRuntimeException ex) {
        HttpStatus status = ex.getStatus();
        String message = ex.getMessage();

        ExceptionResponse<Void> response = new ExceptionResponse<>(false, status, message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        ExceptionResponse<Map<String, String>> response = new ExceptionResponse<>(false, HttpStatus.BAD_REQUEST, "Validation failed" , errors);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse<Void>> handleBaseException(Exception ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = ex.getMessage();

        ExceptionResponse<Void> response = new ExceptionResponse<>(false, status, message);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
