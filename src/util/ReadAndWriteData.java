package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReadAndWriteData {
    public static void writeToFile(File file, List<String> data, boolean append){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
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
