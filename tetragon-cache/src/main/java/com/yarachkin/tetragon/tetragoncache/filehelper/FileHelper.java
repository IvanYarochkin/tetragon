package com.yarachkin.tetragon.tetragoncache.filehelper;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;

import java.io.IOException;
import java.util.Properties;

public class FileHelper {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String propertyPath;
    private Properties properties;

    private FileHelper() {
        propertyPath = FILE_PROPERTIES;
    }

    private static class SingletonHolder {
        private static final FileHelper INSTANCE = new FileHelper();
    }

    public static FileHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setPropertyPath(String propertyPath) throws CacheTetragonException {
        this.propertyPath = propertyPath;
        loadProperties();
    }

    public void setDefaultPropertyPath() throws CacheTetragonException {
        this.propertyPath = FILE_PROPERTIES;
        loadProperties();
    }

    public String acquireFilePath() throws CacheTetragonException {
        if (properties == null) {
            loadProperties();
        }
        return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
    }

    public void loadProperties(Properties properties) throws CacheTetragonException {
        this.properties = properties;
    }

    private void loadProperties() throws CacheTetragonException {
        try {
            properties = new Properties();
            properties.load(FileHelper.class.getResourceAsStream(propertyPath));
        } catch (IOException e) {
            throw new CacheTetragonException("Unable to open " + FILE_PROPERTIES, e);
        }
    }
}
