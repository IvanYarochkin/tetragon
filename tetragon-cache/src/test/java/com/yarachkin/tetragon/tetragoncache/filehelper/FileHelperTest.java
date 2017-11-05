package com.yarachkin.tetragon.tetragoncache.filehelper;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

@Test(singleThreaded = true)
public class FileHelperTest {
    private String filePath;
    private Properties properties;


    @BeforeClass
    public void setUp() throws Exception {
        properties = new Properties();
        properties.load(FileHelperTest.class.getResourceAsStream("/file_helper_test.properties"));
        FileHelper.getInstance().loadProperties(properties);
        filePath = properties.getProperty("file.path") + properties.getProperty("file.name");

        Files.createFile(Paths.get(filePath));
    }

    @AfterClass
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        FileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void acquireFilePathTest() throws CacheTetragonException {
        assertEquals(FileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void loadPropertiesTest() throws CacheTetragonException {
        FileHelper.getInstance().loadProperties(properties);
        assertEquals(FileHelper.getInstance().acquireFilePath(), filePath);
    }

    @Test
    public void setDefaultPropertyPathTest() throws CacheTetragonException, IOException {
        FileHelper.getInstance().setDefaultPropertyPath();
        Properties defaultProperties = new Properties();
        defaultProperties.load(FileHelperTest.class.getResourceAsStream("/file.properties"));
        String defaultPath = defaultProperties.getProperty("file.path") + defaultProperties.getProperty("file.name");
        assertEquals(FileHelper.getInstance().acquireFilePath(), defaultPath);
    }

    @Test
    public void setPropertyPathTest() throws CacheTetragonException, IOException {
        FileHelper.getInstance().setPropertyPath("/file_helper_test.properties");

        assertEquals(FileHelper.getInstance().acquireFilePath(), filePath);
    }
}