package com.yarachkin.tetragon.cache.writer;

import com.yarachkin.tetragon.cache.cache.Cache;
import com.yarachkin.tetragon.cache.exception.CacheTetragonException;
import com.yarachkin.tetragon.cache.reader.Reader;
import com.yarachkin.tetragon.model.entity.Tetragon;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Writer {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String filePath;

    private Writer() throws CacheTetragonException {
        try {
            Properties properties = new Properties();
            properties.load(Writer.class.getResourceAsStream(FILE_PROPERTIES));

            filePath = properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
        } catch (IOException e) {
            throw new CacheTetragonException("Unable to open " + FILE_PROPERTIES, e);
        }
    }

    public static Writer getInstance() {
        return Writer.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {

        private static final Writer INSTANCE;

        static {
            try {
                INSTANCE = new Writer();
            } catch (CacheTetragonException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public void write() throws CacheTetragonException {
        Reader.getInstance().createFileIfNotExists();
        cleanFile();
        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Tetragon tetragon : Cache.getInstance().getCache()) {
                bufferedWriter.write(tetragon + "\n");
            }
        } catch (IOException e) {
            throw new CacheTetragonException("Error in file writing.", e);
        }
    }

    private void cleanFile() throws CacheTetragonException {
        try (FileWriter fileWriter = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("");
        } catch (Exception e) {
            throw new CacheTetragonException("Error in file cleaning.", e);
        }
    }
}
