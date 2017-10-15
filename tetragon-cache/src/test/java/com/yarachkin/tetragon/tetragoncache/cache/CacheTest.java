package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.exception.CacheTetragonException;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragoncache.reader.Reader;
import com.yarachkin.tetragon.tetragoncache.writer.Writer;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
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

@Test(singleThreaded = true)
public class CacheTest {
    private String filePath;
    private List<Tetragon> tetragons;

    @BeforeClass
    public void setUp() throws Exception {
        IdGenerator.setIsTest(true);
        Properties properties = new Properties();
        properties.load(CacheTest.class.getResourceAsStream("/file_cache_test.properties"));
        FileHelper.getInstance().loadProperties(properties);
        filePath = FileHelper.getInstance().acquireFilePath();

        Files.createFile(Paths.get(filePath));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        bufferedWriter.write(properties.getProperty("file.data"));
        bufferedWriter.close();

        tetragons = new ArrayList<>();
        tetragons.add(new Tetragon(1, new Point(1, 1, 0), new Point(1, 2, 3), new Point(1, 4, 5), new Point(1, 6, 5)));
        tetragons.add(new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 9, 8), new Point(1, 0, 0)));
    }

    @AfterClass
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        FileHelper.getInstance().setDefaultPropertyPath();
        IdGenerator.setIsTest(true);
    }

    @Test
    public void getCacheTest() {
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void addTest() {
        tetragons.add(new Tetragon(1, new Point(1, 2, 2.15), new Point(1, 3, 3.3), new Point(1, 10, 5), new Point(1, 3.2, 1)));
        Cache.getInstance().add(new Tetragon(1, new Point(1, 2, 2.15), new Point(1, 3, 3.3), new Point(1, 10, 5), new Point(1, 3.2, 1)));
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void removeTest() {
        Cache.getInstance().remove(1);
        tetragons.remove(0);
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void updateTest() {
        Cache.getInstance().update(1, new Tetragon(new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        tetragons.set(0, new Tetragon(new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void flush() throws CacheTetragonException {
        Writer.getInstance().write(tetragons);
        List<String> expectedLines = Reader.getInstance().readFromFile();
        Cache.getInstance().flush();
        assertEquals(Reader.getInstance().readFromFile(), expectedLines);
    }
}