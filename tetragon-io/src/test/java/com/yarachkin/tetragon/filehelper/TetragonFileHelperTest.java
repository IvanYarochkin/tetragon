package com.yarachkin.tetragon.filehelper;

import com.yarachkin.tetragon.exception.IOTetragonException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class TetragonFileHelperTest {
    private String filePath;
    private Properties properties;


    @BeforeClass
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(TetragonFileHelperTest.class.getResourceAsStream("/file_helper_test.properties"));
        TetragonFileHelper.getInstance().loadProperties(properties);
        filePath = properties.getProperty("file.path") + properties.getProperty("file.name");

        Files.createFile(Paths.get(filePath));
    }

    @AfterClass
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        TetragonFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void acquireFilePathTest() throws IOTetragonException {
        assertEquals(TetragonFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void loadPropertiesTest() throws IOTetragonException {
        TetragonFileHelper.getInstance().loadProperties(properties);
        assertEquals(TetragonFileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void setDefaultPropertyPathTest() throws IOTetragonException, IOException {
        TetragonFileHelper.getInstance().setDefaultPropertyPath();
        Properties defaultProperties = new Properties();
        defaultProperties.load(TetragonFileHelperTest.class.getResourceAsStream("/file.properties"));
        String defaultPath = defaultProperties.getProperty("file.path") + defaultProperties.getProperty("file.name");
        assertEquals(TetragonFileHelper.getInstance().acquireFilePath(), defaultPath);
    }

    @Test
    public void setPropertyPathTest() throws IOTetragonException, IOException {
        TetragonFileHelper.getInstance().setPropertyPath("/file_helper_test.properties");

        assertEquals(TetragonFileHelper.getInstance().acquireFilePath(), filePath);
    }

}