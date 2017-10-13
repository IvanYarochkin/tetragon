package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LogicalTetragonActionTest {
    private Tetragon tetragon;

    @BeforeMethod
    public void setUp() {
        Point first = new Point(5, 5);
        Point second = new Point(6, 7);
        Point third = new Point(10, 4);
        Point fourth = new Point(7, 4);

        tetragon = new Tetragon(1, first, second, third, fourth);
    }

    @Test
    public void isConvexTest() {
        assertEquals(CheckingTetragonAction.isConvex(tetragon), true);
    }

    @Test
    public void isConvexWithConcaveTetragonTest() {
        tetragon.getFourth().setX(2);
        tetragon.getFourth().setY(3);

        assertEquals(CheckingTetragonAction.isConvex(tetragon), false);
    }

}