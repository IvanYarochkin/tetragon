package com.yarachkin.tetragon.reader;

import com.yarachkin.tetragon.exception.IOTetragonException;
import com.yarachkin.tetragon.filehelper.TetragonFileHelper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public final class TetragonFileReader {
    private static final Logger LOGGER = LogManager.getRootLogger();
    private static final String FILE_MESSAGE = "File ";

    private TetragonFileReader() {

    }

    private static class SingletonHolder {
        private static final TetragonFileReader INSTANCE = new TetragonFileReader();
    }

    public static TetragonFileReader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public List<String> readFromFile() throws IOTetragonException {
        createFileIfNotExists();
        try {
            List<String> lines = Files.lines(Paths.get(TetragonFileHelper.getInstance().acquireFilePath()))
                    .filter(Strings::isNotEmpty)
                    .collect(Collectors.toList());

            return lines;
        } catch (IOException e) {
            throw new IOTetragonException("Read error", e);
        }
    }

    public void createFileIfNotExists() throws IOTetragonException {
        String filePath = TetragonFileHelper.getInstance().acquireFilePath();
        try {
            Path cachePath = Paths.get(filePath);

            if ( Files.notExists(cachePath) ) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + filePath + " created.");
            }
        } catch (IOException e) {
            throw new IOTetragonException("Unable to create " + filePath, e);
        }
    }
}
