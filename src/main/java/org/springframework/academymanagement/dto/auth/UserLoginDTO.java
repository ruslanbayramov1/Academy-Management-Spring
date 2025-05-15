package org.springframework.academymanagement.dto.auth;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(
        @Size( min = 3, max = 32) @Pattern(
                regexp = "^[A-Za-z][A-Za-z0-9_]*$",
                message = "Username must start with a letter and contain only letters, digits, or underscores"
        ) String username,

        @Size( min = 6, max = 16)
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).*$",
                message = "Password must be at least 6 characters long and include uppercase, lowercase, digit, and at least 1 special character"
        ) String password
) { }
