package org.springframework.academymanagement.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.academymanagement.dto.logger.CommonLoggerDTO;
import org.springframework.academymanagement.logger.CommonLogger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class CommonRequestLoggingFilter implements Filter {
    private final CommonLogger commonLogger;

    public CommonRequestLoggingFilter(CommonLogger commonLogger) {
        this.commonLogger = commonLogger;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ip = request.getRemoteAddr();
        String uri = request.getRequestURI();
        String userAgent = request.getHeader("User-Agent");
        String lang = request.getHeader("Accept-Language");
        String realIp = request.getHeader("X-Forwarded-For");
        LocalDateTime now = LocalDateTime.now();
        Path path = Paths.get(System.getProperty("user.dir"))
                .resolve("logs")
                .resolve("common")
                .resolve(now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".txt");

        if (!Objects.equals(uri, "/favicon.ico"))
            commonLogger.log(new CommonLoggerDTO(now, ip, uri, userAgent, lang, realIp), path.toString());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
