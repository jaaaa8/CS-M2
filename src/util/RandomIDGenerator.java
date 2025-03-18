package util;

import model.Customer;
import model.Employee;
import model.Leader;
import model.Manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RandomIDGenerator {
    private static final Map<String, Integer> idCounters = new HashMap<>();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyH");

    public static synchronized String generateID(Class<?> clazz) {
        // Xác định tiền tố (prefix) dựa trên class
        String prefix = getPrefix(clazz);
        if (prefix == null) {
            throw new IllegalArgumentException("Unsupported class: " + clazz.getSimpleName());
        }

        // Lấy timestamp hiện tại theo định dạng "ddMMyyH"
        String timestamp = dateFormat.format(new Date());

        String key = prefix + timestamp;
        int count = idCounters.getOrDefault(key, 0);

        String uniqueID = key + String.format("%02d", count);
        idCounters.put(key, count + 1);

        return uniqueID;
    }

    private static String getPrefix(Class<?> clazz) {
        if (clazz == Employee.class) return "E";
        if (clazz == Customer.class) return "C";
        if (clazz == Manager.class) return "M";
        if (clazz == Leader.class) return "L";
        return null;
    }

}

