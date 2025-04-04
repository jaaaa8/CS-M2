package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IDByTimeStampGenerator {
    public static int generateId() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        String formattedTime = now.format(formatter);
        return Integer.parseInt(formattedTime);
    }
}
