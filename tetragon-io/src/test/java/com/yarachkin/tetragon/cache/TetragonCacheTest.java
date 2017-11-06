package com.yarachkin.tetragon.cache;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.IOTetragonException;
import com.yarachkin.tetragon.filehelper.TetragonFileHelper;
import com.yarachkin.tetragon.reader.TetragonFileReader;
import com.yarachkin.tetragon.util.IdGenerator;
import com.yarachkin.tetragon.writer.TetragonFileWriter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;

public class TetragonCacheTest {
    private String filePath;
    private List<Tetragon> tetragons;

    @BeforeClass
    public void setUp() throws Exception {
        IdGenerator.setIsTest(true);
        Properties properties = new Properties();
        properties.load(TetragonCacheTest.class.getResourceAsStream("/file_cache_test.properties"));
        TetragonFileHelper.getInstance().loadProperties(properties);

        filePath = TetragonFileHelper.getInstance().acquireFilePath();
        Files.createFile(Paths.get(filePath));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        bufferedWriter.write(properties.getProperty("file.data"));
        bufferedWriter.close();

        TetragonCache.getInstance().refillCache();
        tetragons = new ArrayList<>();
        tetragons.add(new Tetragon(1, new Point(1, 1, 0), new Point(1, 2, 3), new Point(1, 4, 5), new Point(1, 6, 5)));
        tetragons.add(new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 9, 8), new Point(1, 0, 0)));
    }

    @AfterClass
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        TetragonFileHelper.getInstance().setDefaultPropertyPath();
        IdGenerator.setIsTest(true);
    }

    @Test
    public void getCacheTest() {
        assertEquals(TetragonCache.getInstance().getCache(), tetragons);
    }

    @Test
    public void addTest() {
        tetragons.add(new Tetragon(1, new Point(1, 2, 2.15), new Point(1, 3, 3.3), new Point(1, 10, 5), new Point(1, 3.2, 1)));
        TetragonCache.getInstance().add(new Tetragon(1, new Point(1, 2, 2.15), new Point(1, 3, 3.3), new Point(1, 10, 5), new Point(1, 3.2, 1)));
        assertEquals(TetragonCache.getInstance().getCache(), tetragons);
    }

    @Test
    public void removeTest() {
        TetragonCache.getInstance().remove(1);
        tetragons.remove(0);
        assertEquals(TetragonCache.getInstance().getCache(), tetragons);
    }

    @Test
    public void updateTest() {
        TetragonCache.getInstance().update(1, new Tetragon(new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        tetragons.set(0, new Tetragon(new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        assertEquals(TetragonCache.getInstance().getCache(), tetragons);
    }

    @Test
    public void flush() throws IOTetragonException {
        TetragonFileWriter.getInstance().write(tetragons);
        List<String> expectedLines = TetragonFileReader.getInstance().readFromFile();
        TetragonCache.getInstance().flush();
        assertEquals(TetragonFileReader.getInstance().readFromFile(), expectedLines);
    }
}