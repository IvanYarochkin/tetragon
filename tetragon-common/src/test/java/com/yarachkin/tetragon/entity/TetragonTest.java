package com.yarachkin.tetragon.entity;

import com.yarachkin.tetragon.observer.TetragonDataSet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TetragonTest {
    private Tetragon firstTetragon;

    @BeforeMethod
    public void setUp() throws Exception {
        firstTetragon = new Tetragon(1, new Point(0, 0), new Point(3, 0), new Point(3, 4), new Point(0, 4));
        TetragonDataSet.getInstance().addAdditionalData(firstTetragon);
    }

    @Test
    public void checkChangedTetragonObserverPerimeterTest() {
        firstTetragon.setSecond(new Point(4, 0));
        firstTetragon.setThird(new Point(4, 4));
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getPerimeter(), 16, 0.000000001);
    }

    @Test
    public void checkChangedTetragonObserverAreaTest() {
        firstTetragon.setSecond(new Point(4, 0));
        firstTetragon.setThird(new Point(4, 4));
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getArea(), 16, 0.000000001);
    }

    @AfterClass
    public void tearDown(){
        TetragonDataSet.getInstance().removeAdditionalData(firstTetragon.getId());
    }
}