package util;

import model.Employee;
import model.Project;

import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CreateProjectFileData {
    public static void createProjectFile(String folderPath, Project project) {
        try {
            File folder = new File(folderPath);
            if (!folder.exists() && !folder.mkdir()) {
                System.out.println("Failed to create directory: " + folderPath);
                return;
            }

            String typeCode = getType(project.getTypeOfProject());
            String customerName = cleanWhitespace(project.getCustomer().getName());
            int nextIndex = getNextIndex(folder, typeCode, customerName);

            // Tạo tên file theo format 002EXJACK.csv
            String fileName = String.format("%03d%s%s.csv", nextIndex,typeCode, customerName);
            File newFile = new File(folder, fileName);

            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());

                // Ghi thông tin vào file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                    writer.write("Project Name: " + project.getProjectName());
                    writer.newLine();
                    writer.write("Customer: " + project.getCustomer().getId());
                    writer.newLine();
                    writer.write("Project Type: " + project.getTypeOfProject());
                    writer.newLine();
                    writer.write("Leader: " + project.getLeader().getId());
                    writer.newLine();
                    List<Employee> employees = project.getEmployees(); // Giả sử có phương thức này
                    String employeeIds = employees.stream()
                            .map(Employee::getId)
                            .reduce((id1, id2) -> id1 + "," + id2)
                            .orElse("None"); // Nếu không có nhân viên nào, ghi "None"
                    writer.write("List employees: " + employeeIds);
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
        return switch (typeOfProject.toUpperCase()) {
            case "RENOVATION" -> "RE";
            case "EXPAND" -> "EX";
            case "COMMENCEMENT" -> "CO";
            default -> "UN"; // Unknown
        };
    }

    private static int getNextIndex(File folder, String typeCode, String customerName) {
        File[] files = folder.listFiles((dir, name) -> name.matches("\\d{3}" + typeCode + customerName + "\\.csv"));
        if (files == null || files.length == 0) {
            return 1;
        }

        Arrays.sort(files, Comparator.comparingInt(f -> extractIndex(f.getName())));
        return extractIndex(files[files.length - 1].getName()) + 1;
    }

    private static int extractIndex(String fileName) {
        try {
            return Integer.parseInt(fileName.substring(0, 3));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public static String cleanWhitespace(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", "");
    }
}
