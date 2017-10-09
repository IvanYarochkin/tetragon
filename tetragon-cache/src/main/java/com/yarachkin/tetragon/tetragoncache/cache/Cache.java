package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheException;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
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
import java.util.Objects;
import java.util.Properties;

public class Cache {
    private static final Logger LOGGER = LogManager.getRootLogger();

    private static final String CACHE_PROPERTIES = "/cache.properties";
    private static final String CACHE_FILE_DIRECTORY = "cache.file.path";
    private static final String CACHE_FILE_NAME = "cache.file.name";

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

    public ArrayList<Tetragon> getCache() {
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
                    .forEach(l -> cache.add(lineToTetragon(l)));
        } catch (FileNotFoundException e) {
            throw new CacheException("File " + cacheFilePath + " not found.", e);
        }
    }

    private void createFileIfNotExists() throws CacheException {
        try {
            Path cachePath = Paths.get(cacheFilePath);

            if (Files.notExists(cachePath)) {
                LOGGER.log(Level.INFO, "File " + cacheFilePath + " does not exists.");
                Files.createFile(cachePath);
                LOGGER.log(Level.INFO, "File " + cacheFilePath + " created.");
            }
        } catch (IOException e) {
            throw new CacheException("Unable to create " + cacheFilePath, e);
        }
    }

    private Tetragon lineToTetragon(String line) {
        String[] splittedLine = line.split("[ ]");

        Point firstPoint = new Point(Double.parseDouble(splittedLine[0]), Double.parseDouble(splittedLine[1]));
        Point secondPoint = new Point(Double.parseDouble(splittedLine[2]), Double.parseDouble(splittedLine[3]));
        Point thirdPoint = new Point(Double.parseDouble(splittedLine[4]), Double.parseDouble(splittedLine[5]));
        Point fourthPoint = new Point(Double.parseDouble(splittedLine[6]), Double.parseDouble(splittedLine[7]));

        Tetragon tetragon = new Tetragon(firstPoint, secondPoint, thirdPoint, fourthPoint);

        return tetragon;
    }
}
