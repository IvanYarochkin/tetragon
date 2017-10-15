package com.yarachkin.tetragon.tetragoncache.reader;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Reader {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";
    private static final String FILE_MESSAGE = "File ";

    private Reader() {

    }

    private static class SingletonHolder {
        private static final Reader INSTANCE = new Reader();
    }

    public static Reader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String acquireFilePath() throws CacheTetragonException {
        try {
            Properties properties = new Properties();
            properties.load(Reader.class.getResourceAsStream(FILE_PROPERTIES));

            return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
        } catch (IOException e) {
            throw new CacheTetragonException("Unable to open " + FILE_PROPERTIES, e);
        }
    }


    public List<String> readFromFile(String filePath) throws CacheTetragonException {
        createFileIfNotExists(filePath);
        try {
            List<String> lines = new ArrayList<>();

            Files.lines(Paths.get(filePath))
                    .filter(Strings::isNotEmpty)
                    .forEach(lines::add);
            return lines;
        } catch (IOException e) {
            throw new CacheTetragonException("Read error", e);
        }
    }

    public void createFileIfNotExists(String filePath) throws CacheTetragonException {
        try {
            Path cachePath = Paths.get(filePath);

            if (Files.notExists(cachePath)) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + acquireFilePath() + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + acquireFilePath() + " created.");
            }
        } catch (IOException e) {
            throw new CacheTetragonException("Unable to create " + acquireFilePath(), e);
        }
    }
}
