package com.yarachkin.tetragon.observer;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TetragonDataSetTest {
    Tetragon firstTetragon;

    @BeforeMethod
    public void setUp() throws Exception {
        firstTetragon = new Tetragon(1, new Point(0, 0), new Point(3, 0), new Point(3, 4), new Point(0, 4));
        System.out.println(firstTetragon);
    }

    @Test
    public void checkPerimeterUsingAddAdditionalDataMethodTest(){
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getPerimeter(), 14, 0.000000001);
    }

    @Test
    public void checkAreaUsingAddAdditionalDataMethodTest(){
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getArea(), 12, 0.000000001);
    }

    @Test
    public void addSameTetragonTest(){
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().size(), 1);
    }

    @Test
    public void removeAdditionalDataTest(){
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
        TetragonDataSet.getInstance().removeAdditionalData(firstTetragon.getId());
        assertTrue(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().isEmpty());
    }

    @AfterClass
    public void tearDown(){
        TetragonDataSet.getInstance().removeAdditionalData(firstTetragon.getId());
    }

}