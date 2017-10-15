package com.yarachkin.tetragon.tetragoncache.reader;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;

public class ReaderTest {

    private static final String FILE_PROPERTIES = "/file.properties";
    private static final String FILE_DIRECTORY = "file.path.test";
    private static final String FILE_NAME = "file.name.test";

    private String filePath;
    List<String> lines;

    @BeforeClass
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(Reader.class.getResourceAsStream(FILE_PROPERTIES));

        filePath = properties.getProperty(FILE_DIRECTORY) + properties.getProperty(FILE_NAME);

        Files.createFile(Paths.get(filePath));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        bufferedWriter.write("1.0 1.0 2.0 2.0 3.0 3.0 4.0 4.0\n" +
                "2.0 2.0 3v.0 3.0 4.0 4.0 5z.0 5.0\n" +
                "5 5 8\n" +
                "8 84 4 7 9 3 6 5 4 7 8 5 2 2");
        bufferedWriter.close();

        lines = new ArrayList<>();
        lines.add("1.0 1.0 2.0 2.0 3.0 3.0 4.0 4.0");
        lines.add("2.0 2.0 3v.0 3.0 4.0 4.0 5z.0 5.0");
        lines.add("5 5 8");
        lines.add("8 84 4 7 9 3 6 5 4 7 8 5 2 2");
    }

    @AfterClass
    public void tearDown() throws IOException {
        Files.delete(Paths.get(filePath));
        Files.delete(Paths.get("src/test/resources/test_created_file.txt"));
    }

    @Test
    public void readFromFileTest() throws Exception {
        assertEquals(Reader.getInstance().readFromFile(filePath), lines);
    }

    @Test
    public void createFileIfNotExistsTest() throws CacheTetragonException {
        Reader.getInstance().createFileIfNotExists("src/test/resources/test_created_file.txt");
        assertFalse(Files.notExists(Paths.get("src/test/resources/test_created_file.txt")));
    }
}