package com.yarachkin.tetragon.tetragoncache.cache;

import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedWriter;
import java.io.File;
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
    private File file;
    private static final Logger LOGGER = LogManager.getRootLogger();

    @BeforeMethod
    public void setUp() throws Exception {
        IdGenerator.setIsTest(true);
        Properties properties = new Properties();
        properties.load(CacheTest.class.getResourceAsStream("/file_cache_test.properties"));
        FileHelper.getInstance().loadProperties(properties);
        filePath = FileHelper.getInstance().acquireFilePath();

        file = new File(filePath);
        file.createNewFile();

//        Files.createFile(Paths.get(filePath));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

        bufferedWriter.write(properties.getProperty("file.data"));
        bufferedWriter.close();

        tetragons = new ArrayList<>();
        tetragons.add(new Tetragon(1,new Point(1,1, 0), new Point(1,2, 3), new Point(1,4, 5), new Point(1,6, 5)));
        tetragons.add(new Tetragon(1,new Point(1,1, 1), new Point(1,4, 3), new Point(1,9, 8), new Point(1,0, 0)));
    }

    @AfterMethod
    public void tearDown() throws Exception {
       file.delete();
        FileHelper.getInstance().setDefaultPropertyPath();
        IdGenerator.setIsTest(true);
    }

    @Test
    public void getCacheTest() {
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void addTest() {
        tetragons.add(new Tetragon(1,new Point(1,2, 2.15), new Point(1,3, 3.3), new Point(1,10, 5), new Point(1,3.2, 1)));
        Cache.getInstance().add(new Tetragon(1,new Point(1,2, 2.15), new Point(1,3, 3.3), new Point(1,10, 5), new Point(1,3.2, 1)));
        Cache.getInstance().getCache().forEach(System.out::println);
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

}
