package org.springframework.academymanagement.logger;

import org.springframework.academymanagement.dto.logger.CommonLoggerDTO;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class CommonLogger {
    public void log(CommonLoggerDTO dto, String filePath) {
        createParentDirectories(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(dto.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void createParentDirectories(String filePath) {
        try {
            Path dir = Path.of(filePath).getParent();
            if (!Files.exists(dir))
            {
                Files.createDirectories(dir);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
