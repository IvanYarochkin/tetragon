package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheException;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;

public class Cache {
    private ArrayList<Tetragon> cache;
    private static final String FILE_PATH = "/tetragons.txt";

    static final Logger LOGGER = LogManager.getRootLogger();

    private Cache() {
        readFromFile();
    }

    private static class SingletonHelper {
        private static final Cache INSTANCE = new Cache();
    }

    public static Cache getInstance() {
        return SingletonHelper.INSTANCE;
    }

    public ArrayList<Tetragon> getCache() {
        ArrayList<Tetragon> tempCache = new ArrayList<>();
        tempCache.addAll(cache);
        return tempCache;
    }

    private void readFromFile() {
        cache = new ArrayList<>();
        try {
            if (Cache.class.getResource(FILE_PATH) == null) {
                throw new CacheException("Файл не найден.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(Cache.class.getResourceAsStream(FILE_PATH)));

            String line;
            while ((line = reader.readLine()) != null) {
                cache.add(lineToTetragon(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CacheException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    private Tetragon lineToTetragon(String line){
        String[] splittedLine = line.split("[ ]");

        Point firstPoint = new Point(Double.parseDouble(splittedLine[0]), Double.parseDouble(splittedLine[1]));
        Point secondPoint = new Point(Double.parseDouble(splittedLine[2]), Double.parseDouble(splittedLine[3]));
        Point thirdPoint = new Point(Double.parseDouble(splittedLine[4]), Double.parseDouble(splittedLine[5]));
        Point fourthPoint = new Point(Double.parseDouble(splittedLine[6]), Double.parseDouble(splittedLine[7]));

        Tetragon tetragon = new Tetragon(firstPoint, secondPoint, thirdPoint, fourthPoint);

        return tetragon;
    }
}
