package org.springframework.academymanagement.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record StudentCreateDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        LocalDate birthDate,
        @NotBlank String gender,
        @NotBlank String studentCode,
        @NotBlank String groupCode
) {
}
