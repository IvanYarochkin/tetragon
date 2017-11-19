package com.yarachkin.tetragon.entity;

import com.yarachkin.tetragon.observer.TetragonDataSet;
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
    public void checkTetragonObserverPerimeterTest() {
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getPerimeter(), 14, 0.000000001);
    }

    @Test
    public void checkTetragonObserverAreaTest() {
        assertEquals(TetragonDataSet.getInstance().getAdditionalTetragonDataSet().get(1L).getArea(), 12, 0.000000001);
    }
}