package com.yarachkin.tetragon.tetragoncache.reader;

import com.yarachkin.tetragon.tetragoncache.cache.Cache;
import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonutil.converter.TetragonConverter;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import com.yarachkin.tetragon.tetragonutil.parser.LineParser;
import com.yarachkin.tetragon.tetragonutil.validator.TetragonValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Reader {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";
    private static final String FILE_MESSAGE = "File ";

    private String filePath;

    private Reader() throws CacheTetragonException {
        try {
            Properties properties = new Properties();
            properties.load(Reader.class.getResourceAsStream(FILE_PROPERTIES));

            filePath = properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);

        } catch (IOException e) {
            throw new CacheTetragonException("Unable to open " + FILE_PROPERTIES, e);
        }
    }

    private static class SingletonHolder {

        private static final Reader INSTANCE;

        static {
            try {
                INSTANCE = new Reader();
            } catch (CacheTetragonException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static Reader getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void readFromFile() throws CacheTetragonException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))))) {
            createFileIfNotExists();
            reader.lines()
                    .filter(Strings::isNotEmpty)
                    .forEach(this::addToCache);
        } catch (FileNotFoundException e) {
            throw new CacheTetragonException(FILE_MESSAGE + filePath + "did not find.", e);
        } catch (IOException e) {
            throw new CacheTetragonException("Read error", e);
        }
    }

    public void createFileIfNotExists() throws CacheTetragonException {
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

    private void addToCache(String line) {
        try {
            TetragonDto tetragonDto = LineParser.parse(line, "[ ]");
            if (TetragonValidator.validate(tetragonDto)) {
                Cache.getInstance().add(TetragonConverter.convert(tetragonDto));
            }
        } catch (UtilTetragonException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }
}
