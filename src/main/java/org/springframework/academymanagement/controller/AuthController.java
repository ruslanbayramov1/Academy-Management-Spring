package org.springframework.academymanagement.controller;

import jakarta.validation.Valid;
import org.springframework.academymanagement.dto.response.ApiResponse;
import org.springframework.academymanagement.dto.auth.UserLoginDTO;
import org.springframework.academymanagement.dto.auth.UserRegisterDTO;
import org.springframework.academymanagement.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService userService) {
        this.authService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> registerUser(@RequestBody @Valid final UserRegisterDTO dto) {
        authService.register(dto);
        ApiResponse<Void> response = new ApiResponse<>(true, HttpStatus.CREATED, "User registered successfully");
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> loginUser(@RequestBody @Valid final UserLoginDTO dto) {
        String token = authService.login(dto);
        ApiResponse<String> response = new ApiResponse<>(true, HttpStatus.OK, "User registered successfully", token);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
