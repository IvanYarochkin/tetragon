package com.yarachkin.tetragon.tetragoncache.reader;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Reader {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILE_MESSAGE = "File ";

    private Reader() {

    }

    private static class SingletonHolder {
        private static final Reader INSTANCE = new Reader();
    }

    public static Reader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String> readFromFile() throws CacheTetragonException {
        createFileIfNotExists();
        try {
            List<String> lines = new ArrayList<>();

            Files.lines(Paths.get(FileHelper.getInstance().acquireFilePath()))
                    .filter(Strings::isNotEmpty)
                    .forEach(lines::add);
            return lines;
        } catch (IOException e) {
            throw new CacheTetragonException("Read error", e);
        }
    }

    public void createFileIfNotExists() throws CacheTetragonException {
        String filePath = FileHelper.getInstance().acquireFilePath();
        try {
            Path cachePath = Paths.get(filePath);

            if (Files.notExists(cachePath)) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new CacheTetragonException("Unable to create " + filePath, e);
        }
    }
}
