package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.entity.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CalculatingTriangleActionTest {

    private Point first = new Point(2, 0);
    private Point second = new Point(0, 0);
    private Point third = new Point(0, 2);

    @BeforeMethod
    public void setUp() {

    }

    @Test
    public void calculateAreaTest() {
        assertEquals(CalculatingTriangleAction.calculateArea(first, second, third), 2.0, 0.000000001);
    }

    @Test
    public void calculateRotatedTriangleAreaTest() {
        first.setX(0);
        first.setY(4);
        second.setX(2);
        second.setY(6);
        third.setX(2);
        third.setY(2);

        assertEquals(CalculatingTriangleAction.calculateArea(first, second, third), 4, 0.000000001);
    }

}