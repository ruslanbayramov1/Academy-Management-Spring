package org.springframework.academymanagement.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        @Size( min = 2, max = 64) @Pattern(
                regexp = "^[A-Za-z]+$",
                message = "First name can only contain letters"
        ) String firstName,

        @Size( min = 2, max = 64) @Pattern(
                regexp = "^[A-Za-z]+$",
                message = "Last name can only contain letters"
        ) String lastName,

        @Size( min = 3, max = 32) @Pattern(
                regexp = "^[A-Za-z][A-Za-z0-9_]*$",
                message = "Username must start with a letter and contain only letters, digits, or underscores"
        ) String username,

        @Size( min = 6, max = 16)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$",
                message = "Password must be at least 6 characters long and include uppercase, lowercase, digit, and at least 1 special character"
        ) String password,

        @NotBlank String passwordConfirm,

        @NotBlank @Pattern(
                regexp = "^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$",
                message = "Email address must be valid"
        ) String email
) { }
