package com.yarachkin.tetragon.tetragoncache.writer;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragoncache.reader.Reader;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.Assert.assertEquals;

public class WriterTest {
    private String filePath;
    private List<String> expectedLines;
    private List<Tetragon> tetragons;

    @BeforeMethod
    public void setUp() throws CacheTetragonException, IOException {
        Properties properties = new Properties();
        properties.load(WriterTest.class.getResourceAsStream("/file_writer_test.properties"));
        FileHelper.getInstance().loadProperties(properties);
        filePath = FileHelper.getInstance().acquireFilePath();
        Files.createFile(Paths.get(filePath));

        tetragons = new ArrayList<>();
        tetragons.add(new Tetragon(new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)));
        expectedLines = new ArrayList<>();
        expectedLines.add(properties.getProperty("file.data"));
    }


    @AfterMethod
    public void tearDown() throws IOException, CacheTetragonException {
        Files.delete(Paths.get(filePath));
        FileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void writeTest() throws CacheTetragonException, IOException {
        Writer.getInstance().write(tetragons);
        assertEquals(Reader.getInstance().readFromFile(), expectedLines);
    }

}