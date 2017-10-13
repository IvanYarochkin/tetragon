package com.yarachkin.tetragon.tetragonaction.action;


import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TetragonCalculatingActionTest {
    private Tetragon tetragon;

    @BeforeMethod
    public void setUp() {
        Point first = new Point(0, 0);
        Point second = new Point(0, 1);
        Point third = new Point(1, 1);
        Point fourth = new Point(1, 0);

        tetragon = new Tetragon(1, first, second, third, fourth);
    }

    @Test
    public void calculatePerimeterTest() {
        assertEquals(TetragonCalculatingAction.calculatePerimeter(tetragon), 4.0);
    }

    @Test
    public void calculateRotatedTetragonPerimeterTest() {
        tetragon.getFirst().setX(3);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(3);
        tetragon.getThird().setX(3);
        tetragon.getThird().setY(5);
        tetragon.getFourth().setX(5);
        tetragon.getFourth().setY(3);

        assertEquals(TetragonCalculatingAction.calculatePerimeter(tetragon), 4 * Math.sqrt(8));
    }

    @Test
    public void calculateRectanglePerimeterTest() {
        tetragon.getFirst().setX(1);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(2);
        tetragon.getThird().setX(10);
        tetragon.getThird().setY(2);
        tetragon.getFourth().setX(10);
        tetragon.getFourth().setY(1);

        assertEquals(TetragonCalculatingAction.calculatePerimeter(tetragon), 20.0);
    }

    @Test
    public void calculateAreaTest() {
        assertEquals(TetragonCalculatingAction.calculateArea(tetragon), 1.0);
    }

    @Test
    public void calculateRotatedTetragonAreaTest() {
        tetragon.getFirst().setX(3);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(3);
        tetragon.getThird().setX(3);
        tetragon.getThird().setY(5);
        tetragon.getFourth().setX(5);
        tetragon.getFourth().setY(3);

        assertEquals(TetragonCalculatingAction.calculateArea(tetragon), 8.0);
    }

    @Test
    public void calculateRectangleAreaTest() {
        tetragon.getFirst().setX(1);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(2);
        tetragon.getThird().setX(10);
        tetragon.getThird().setY(2);
        tetragon.getFourth().setX(10);
        tetragon.getFourth().setY(1);

        assertEquals(TetragonCalculatingAction.calculateArea(tetragon), 9.0);
    }


}