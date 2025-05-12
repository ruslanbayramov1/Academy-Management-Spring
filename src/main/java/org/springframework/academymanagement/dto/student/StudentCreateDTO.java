package org.springframework.academymanagement.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record StudentCreateDTO(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        LocalDate birthDate,
        @NotBlank String gender,
        @NotBlank String studentCode,
        @NotNull UUID groupId
) {
}
