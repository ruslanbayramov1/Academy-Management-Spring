package org.springframework.academymanagement.dto.student;


import java.time.LocalDate;
import java.util.UUID;

public record StudentGetDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate,
        String gender,
        String studentCode,
        String groupName
) { }
