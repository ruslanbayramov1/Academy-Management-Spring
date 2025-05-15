package org.springframework.academymanagement.dto.auth;

public record JwtTokenGenerateDTO (
        String username,
        String email,
        String firstName,
        String lastName
) { }
