package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteData {
    public static void writeToFile(String filePath, List<String> data, boolean append){
        File file = new File(filePath);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,append))) {
            for (String s : data) {
                bw.write(s);
                bw.newLine();
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("I/O exception!");;
        }
    }

    public static List<String> readFile(String filePath){
        File file = new File(filePath);
        List<String> src = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                src.add(line);
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("I/O exception!");;
        }
        return src;
    }

    public static List<String> readFile(File file){
        List<String> src = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                src.add(line);
            }
        } catch (FileNotFoundException e){
            System.err.println("File not found!");
        } catch (IOException e) {
            System.err.println("I/O exception!");;
        }
        return src;
    }
}
