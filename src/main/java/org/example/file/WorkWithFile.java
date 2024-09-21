package org.example.file;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFile {
    private List<String> strings;
    private static final String FILE_NAME = "battle_log.txt";

    public WorkWithFile() {
        strings = new ArrayList<>();
    }

    public void addAction(String action) {
        strings.add(action);
    }

    public void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(FILE_NAME);
        for (String action : strings) {
            writer.write(action + "\n");
        }
        writer.close();
    }

    public List<String> loadFromFile() throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get(FILE_NAME));
        strings = new ArrayList<>(fileContent);
        return fileContent;
    }

    public List<String> getStrings() {
        return strings;
    }
}
