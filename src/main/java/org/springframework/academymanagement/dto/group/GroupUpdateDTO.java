package org.springframework.academymanagement.dto.group;

import jakarta.validation.constraints.NotBlank;

public record GroupUpdateDTO(
        @NotBlank String name
) { }
