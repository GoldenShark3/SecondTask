package com.epam.jwd.text_handling.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader {
    private static TextReader instance;
    private final Logger LOGGER = LogManager.getLogger(TextReader.class);
    private final String FILE_PATH = "src/main/resources/input/";

    private TextReader() {
    }

    public static TextReader getInstance() {
        if (instance == null) {
            instance = new TextReader();
        }
        return instance;
    }

    public String receiveTextFromFile(String fileName) {
        LOGGER.info("Start reading file");
        File file = new File(FILE_PATH + fileName + ".txt");
        StringBuilder textFromFile = new StringBuilder();
        try(Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                textFromFile.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("Can't find file: " + file.getAbsolutePath());
            System.exit(1);
        }
        return textFromFile.toString();
    }


}
