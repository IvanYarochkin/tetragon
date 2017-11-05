package com.yarachkin.tetragon.filehelper;

import com.yarachkin.tetragon.exception.IOTetragonException;

import java.io.IOException;
import java.util.Properties;

public final class TetragonFileHelper {
    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path";
    private static final String FILE_NAME = "file.name";

    private String propertyPath;
    private Properties properties;

    private TetragonFileHelper() {
        propertyPath = FILE_PROPERTIES;
    }

    private static class SingletonHolder {
        private static final TetragonFileHelper INSTANCE = new TetragonFileHelper();
    }

    public static TetragonFileHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void setPropertyPath(String propertyPath) throws IOTetragonException {
        this.propertyPath = propertyPath;
        loadProperties();
    }

    public void setDefaultPropertyPath() throws IOTetragonException {
        this.propertyPath = FILE_PROPERTIES;
        loadProperties();
    }

    public String acquireFilePath() throws IOTetragonException {
        if ( properties == null ) {
            loadProperties();
        }
        return properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);
    }

    public void loadProperties(Properties properties) {
        this.properties = properties;
    }

    private void loadProperties() throws IOTetragonException {
        try {
            properties = new Properties();
            properties.load(TetragonFileHelper.class.getResourceAsStream(propertyPath));
        } catch (IOException e) {
            throw new IOTetragonException("Unable to open " + FILE_PROPERTIES, e);
        }
    }
}
