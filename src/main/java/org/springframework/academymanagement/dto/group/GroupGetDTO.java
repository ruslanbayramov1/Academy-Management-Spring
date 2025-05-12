package org.springframework.academymanagement.dto.group;

import java.util.List;
import java.util.UUID;

public record GroupGetDTO(
        UUID id,
        String name
) { }
