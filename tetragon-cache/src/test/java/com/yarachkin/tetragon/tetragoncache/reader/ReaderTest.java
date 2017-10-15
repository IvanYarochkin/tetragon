package com.yarachkin.tetragon.tetragoncache.reader;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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

    private String filePath;
    List<String> lines;

    @BeforeMethod
    public void setUp() throws CacheTetragonException, IOException {
        FileHelper.getInstance().setPropertyPath("/file_test.properties");
        filePath = FileHelper.getInstance().acquireFilePath();
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

    @AfterMethod
    public void tearDown() throws IOException, CacheTetragonException {
        Files.delete(Paths.get(filePath));
        FileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void readFromFileTest() throws Exception {
        assertEquals(Reader.getInstance().readFromFile(), lines);
    }

    @Test
    public void createFileIfNotExistsTest() throws CacheTetragonException {
        Reader.getInstance().createFileIfNotExists();
        assertFalse(Files.notExists(Paths.get(filePath)));
    }
}