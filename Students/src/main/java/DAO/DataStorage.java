package DAO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {


    public static void writeToFile(String path, List<String> lines) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static List<String> readFromFile(String path) throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(path);
        //debug
//        System.out.println(path);
//        System.out.println(file.exists());

        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) lines.add(line);
        }
        return lines;
    }
}
