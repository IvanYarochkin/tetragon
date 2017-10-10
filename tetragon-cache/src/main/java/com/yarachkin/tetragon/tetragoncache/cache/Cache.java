package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheException;
import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.converter.TetragonConverter;
import com.yarachkin.tetragon.tetragonutil.exception.UtilException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Cache {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String CACHE_PROPERTIES = "/cache.properties";
    private static final String CACHE_FILE_DIRECTORY = "cache.file.path";
    private static final String CACHE_FILE_NAME = "cache.file.name";
    private static final String FILE_MESSAGE = "File ";

    private List<Tetragon> cache;
    private String cacheFilePath;

    private Cache() throws CacheException {
        try {
            Properties properties = new Properties();
            properties.load(Cache.class.getResourceAsStream(CACHE_PROPERTIES));

            cacheFilePath = properties.getProperty(CACHE_FILE_DIRECTORY) + properties.getProperty(CACHE_FILE_NAME);

            createFileIfNotExists();
            readFromFile();
        } catch (IOException e) {
            throw new CacheException("Unable to open " + CACHE_PROPERTIES, e);
        }
    }

    private static class SingletonHelper {
        private static final Cache INSTANCE;

        static {
            try {
                INSTANCE = new Cache();
            } catch (CacheException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static Cache getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public List<Tetragon> getCache() {
        ArrayList<Tetragon> tempCache = new ArrayList<>();
        tempCache.addAll(cache);
        return tempCache;
    }

    private void readFromFile() throws CacheException {
        try {
            cache = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(cacheFilePath))));
            reader.lines()
                    .filter(Strings::isNotEmpty)
                    .forEach(line -> addToCache(line));
        } catch (FileNotFoundException e) {
            throw new CacheException(FILE_MESSAGE + cacheFilePath + "did not find.", e);
        }
    }

    private void createFileIfNotExists() throws CacheException {
        try {
            Path cachePath = Paths.get(cacheFilePath);

            if (Files.notExists(cachePath)) {
                LOGGER.log(Level.INFO, FILE_MESSAGE + cacheFilePath + " does not exist.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, FILE_MESSAGE + cacheFilePath + " created.");
            }
        } catch (IOException e) {
            throw new CacheException("Unable to create " + cacheFilePath, e);
        }
    }

    private void addToCache(String line){
        try {
            TetragonDto tetragonDto = lineToTetragonDto(line);
            if (TetragonValidator.validate(tetragonDto)){
                cache.add(TetragonConverter.convert(tetragonDto));
            }
        } catch (UtilException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    private TetragonDto lineToTetragonDto(String line) {
        String[] splittedLine = line.split("[ ]");

        PointDto firstPointDto = new PointDto(splittedLine[0], splittedLine[1]);
        PointDto secondPointDto = new PointDto(splittedLine[2], splittedLine[3]);
        PointDto thirdPointDto = new PointDto(splittedLine[4], splittedLine[5]);
        PointDto fourthPointDto = new PointDto(splittedLine[6], splittedLine[7]);

        return new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);
    }
}
