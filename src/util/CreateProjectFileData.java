package util;

import model.Project;

import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class CreateProjectFileData {
    public static void createProjectFile(String folderPath, Project project) {
        try {
            File folder = new File(folderPath);
            if (!folder.exists() && !folder.mkdir()) {
                System.out.println("Failed to create directory: " + folderPath);
                return;
            }

            String typeCode = getType(project.getTypeOfProject());
            String customerName = project.getCustomer().getName().toLowerCase();
            int nextIndex = getNextIndex(folder, typeCode, customerName);

            // Tạo tên file theo format EXJACK002.txt
            String fileName = String.format("%s%s%03d.csv", typeCode, customerName, nextIndex);
            File newFile = new File(folder, fileName);

            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());

                // Ghi thông tin vào file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                    writer.write("Project Name: " + project.getProjectName());
                    writer.newLine();
                    writer.write("Customer: " + customerName);
                    writer.newLine();
                    writer.write("Project Type: " + project.getTypeOfProject());
                    writer.newLine();
                    writer.write("Leader Name: " + project.getLeader().getName());
                    writer.newLine();
                    writer.write("Start Date: " + project.getStartDate());
                    writer.newLine();
                    writer.write("Expected End Date: " + project.getExpectedEndDate());
                    System.out.println("Project details written to file.");
                }
            } else {
                System.out.println("File already exists: " + newFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error creating/writing file: " + e.getMessage());
        }
    }

    private static String getType(String typeOfProject) {
        return switch (typeOfProject.toLowerCase()) {
            case "renovation" -> "re";
            case "expand" -> "ex";
            case "commencement" -> "co";
            default -> "un"; // Unknown
        };
    }

    private static int getNextIndex(File folder, String typeCode, String customerName) {
        File[] files = folder.listFiles((dir, name) -> name.startsWith(typeCode + customerName));
        if (files == null || files.length == 0) {
            return 1;
        }

        Arrays.sort(files, Comparator.comparingInt(f -> extractIndex(f.getName(), typeCode, customerName)));
        return extractIndex(files[files.length - 1].getName(), typeCode, customerName) + 1;
    }

    private static int extractIndex(String fileName, String typeCode, String customerName) {
        try {
            return Integer.parseInt(fileName.replace(typeCode + customerName, "").replace(".txt", ""));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
