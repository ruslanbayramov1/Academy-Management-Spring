package org.springframework.academymanagement.dto.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<TData> {
    private final boolean success;
    private final String message;
    private final int status;
    private final TData data;

    public ApiResponse(boolean success, HttpStatus status) {
        this.success = success;
        this.status = status.value();

        this.message = "";
        this.data = null;
    }

    public ApiResponse(boolean success, HttpStatus status, String message) {
        this.success = success;
        this.status = status.value();
        this.message = message;

        this.data = null;
    }

    public ApiResponse(boolean success, HttpStatus status, TData data) {
        this.success = success;
        this.status = status.value();
        this.data = data;

        this.message = "";
    }

    public ApiResponse(boolean success, HttpStatus status, String message, TData data) {
        this.success = success;
        this.message = message;
        this.status = status.value();
        this.data = data;
    }
}
