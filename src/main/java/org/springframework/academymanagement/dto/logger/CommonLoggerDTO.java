package org.springframework.academymanagement.dto.logger;

import java.time.LocalDateTime;

public record CommonLoggerDTO (
        LocalDateTime dateTime,
        String ip,
        String uri,
        String userAgent,
        String lang,
        String realIp
) {}


