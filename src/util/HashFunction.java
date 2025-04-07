package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunction {
    public static String hashString(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // Hoặc MD5, SHA-1
            byte[] hashedBytes = md.digest(s.getBytes());

            // Chuyển byte sang hex
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));  // Format sang hex
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error while hashing password");
            return null;
        }
    }
}
