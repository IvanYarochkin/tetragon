package com.yarachkin.tetragon.tetragondao.dao.impl;

import com.yarachkin.tetragon.tetragoncache.cache.Cache;
import com.yarachkin.tetragon.tetragoncache.filehelper.FileHelper;
import com.yarachkin.tetragon.tetragondao.dao.TetragonDao;
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
import java.util.Optional;
import java.util.Properties;

import static org.testng.AssertJUnit.assertEquals;

public class TetragonDaoImplTest {
    private String filePath;
    private List<Tetragon> tetragons;
    private TetragonDao tetragonDao;

    @BeforeClass
    public void setUp() throws Exception {
        IdGenerator.setIsTest(true);
        Properties properties = new Properties();
        properties.load(TetragonDaoImplTest.class.getResourceAsStream("/file_dao_test.properties"));
        FileHelper.getInstance().loadProperties(properties);
        filePath = FileHelper.getInstance().acquireFilePath();
        Files.createFile(Paths.get(filePath));

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));

        bufferedWriter.write(properties.getProperty("file.data"));
        bufferedWriter.close();

        tetragonDao = new TetragonDaoImpl();
        tetragons = new ArrayList<>();

        tetragons.add(new Tetragon(1, new Point(1, 1, 0), new Point(1, 2, 3), new Point(1, 4, 5), new Point(1, 6, 5)));
        tetragons.add(new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 9, 8), new Point(1, 0, 0)));
    }

    @AfterClass
    public void tearDown() throws Exception {
        Files.delete(Paths.get(filePath));
        FileHelper.getInstance().setDefaultPropertyPath();
        IdGenerator.setIsTest(false);
    }

    @Test
    public void findAllTest() throws Exception {
        assertEquals(tetragonDao.findAll(), tetragons);
    }

    @Test
    public void createTest() throws Exception {
        tetragons.add(new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 9, 8), new Point(1, 0, 0)));
        tetragonDao.create(new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 9, 8), new Point(1, 0, 0)));
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void findByIdTest() throws Exception {
        assertEquals(tetragonDao.findById(1), Optional.of(tetragons.get(0)));
    }

    @Test
    public void updateTest() throws Exception {
        tetragonDao.update(1, new Tetragon(1, new Point(1, 1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        tetragons.set(0, new Tetragon(1, new Point(1,1, 1), new Point(1, 4, 3), new Point(1, 2, 0), new Point(1, 3, 1)));
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

    @Test
    public void deleteTest() throws Exception {
        tetragonDao.delete(1);
        tetragons.remove(0);
        assertEquals(Cache.getInstance().getCache(), tetragons);
    }

}