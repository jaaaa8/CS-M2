package util;

import model.Employee;
import model.Project;

import java.io.*;
import java.util.Arrays;
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

            // Lấy index lớn nhất hiện có, sau đó tăng lên 1
            int latestIndex = getLatestIndex(folder);
            int nextIndex = latestIndex + 1;

            // Tạo tên file theo format 002EXJACK.csv
            String fileName = String.format("%03d%s%s.csv", nextIndex, typeCode, customerName);
            File newFile = new File(folder, fileName);

            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());

                // Ghi thông tin vào file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
                    writer.write("ID: " + nextIndex);
                    writer.newLine();
                    writer.write("Project Name: " + project.getProjectName());
                    writer.newLine();
                    writer.write("Customer: " + project.getCustomer().getId());
                    writer.newLine();
                    writer.write("Project Type: " + project.getTypeOfProject());
                    writer.newLine();
                    writer.write("Leader: " + project.getLeader().getId());
                    writer.newLine();
                    List<Employee> employees = project.getEmployees();
                    String employeeIds = employees.stream()
                            .map(Employee::getId)
                            .reduce((id1, id2) -> id1 + "," + id2)
                            .orElse("None");
                    writer.write("List employees: " + employeeIds);
                    writer.newLine();
                    writer.write("Start Date: " + project.getStartDate());
                    writer.newLine();
                    writer.write("Expected End Date: " + project.getExpectedEndDate());
                    writer.newLine();
                    writer.write("Pay: " + (project.isPaid() ? "Already." : "Not yet."));
                    System.out.println("Project details written to file.");
                }
            } else {
                System.out.println("File already exists: " + newFile.getName());
            }
        } catch (IOException e) {
            System.out.println("Error creating/writing file: " + e.getMessage());
        }
    }



    public static int getLatestIndex(File folder) {
        File[] files = folder.listFiles((dir, name) -> name.matches("\\d{3}\\w+\\.csv"));

        if (files == null || files.length == 0) {
            return 0; // Nếu chưa có file nào, trả về 0
        }

        return Arrays.stream(files)
                .map(f -> extractIndex(f.getName()))
                .max(Integer::compare)
                .orElse(0);
    }

    private static int extractIndex(String fileName) {
        try {
            return Integer.parseInt(fileName.substring(0, 3));
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            return 0;
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

    public static String cleanWhitespace(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\s+", "").toUpperCase();
    }
}
