package com.yarachkin.tetragon.writer;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.IOTetragonException;
import com.yarachkin.tetragon.filehelper.TetragonFileHelper;
import com.yarachkin.tetragon.reader.TetragonFileReader;
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

public class TetragonFileWriterTest {
    private String filePath;
    private List<String> expectedLines;
    private List<Tetragon> tetragons;

    @BeforeMethod
    public void setUp() throws IOTetragonException, IOException {
        Properties properties = new Properties();
        properties.load(TetragonFileWriterTest.class.getResourceAsStream("/file_writer_test.properties"));
        TetragonFileHelper.getInstance().loadProperties(properties);

        filePath = TetragonFileHelper.getInstance().acquireFilePath();
        Files.createFile(Paths.get(filePath));

        tetragons = new ArrayList<>();
        tetragons.add(new Tetragon(new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)));
        expectedLines = new ArrayList<>();
        expectedLines.add(properties.getProperty("file.data"));
    }


    @AfterMethod
    public void tearDown() throws IOException, IOTetragonException {
        Files.delete(Paths.get(filePath));
        TetragonFileHelper.getInstance().setDefaultPropertyPath();
    }

    @Test
    public void writeTest() throws IOTetragonException, IOException {
        TetragonFileWriter.getInstance().write(tetragons);
        assertEquals(TetragonFileReader.getInstance().readFromFile(), expectedLines);
    }

}