package org.springframework.academymanagement.dto.group;

import jakarta.validation.constraints.NotBlank;

public record GroupCreateDTO(
        @NotBlank String name
) { }
