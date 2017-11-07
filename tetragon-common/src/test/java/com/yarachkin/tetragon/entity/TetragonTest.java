package com.yarachkin.tetragon.entity;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TetragonTest {
    private Tetragon firstTetragon;

    @BeforeMethod
    public void setUp() throws Exception {
        firstTetragon = new Tetragon(new Point(0, 0), new Point(3, 0), new Point(3, 4), new Point(0, 4));
        firstTetragon.registerTetragonObserver(new Perimeter());
        firstTetragon.registerTetragonObserver(new Square());
    }

    @Test
    public void notifyFirstTetragonObserverTest() {
        assertEquals((Double) firstTetragon.getObserverValues().get(0), 14, 0.000000001);
    }

    @Test
    public void notifySecondTetragonObserverTest() {
        assertEquals((Double) firstTetragon.getObserverValues().get(1), 12, 0.000000001);
    }

}