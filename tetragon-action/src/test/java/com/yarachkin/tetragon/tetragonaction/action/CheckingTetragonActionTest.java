package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class CheckingTetragonActionTest {
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
    public void isConvexConcaveTetragonTest() {
        tetragon.getFourth().setX(2);
        tetragon.getFourth().setY(3);

        assertEquals(CheckingTetragonAction.isConvex(tetragon), false);
    }

    @Test
    public void isSquareTest() {
        tetragon.getFirst().setX(2);
        tetragon.getFirst().setY(2);
        tetragon.getSecond().setX(2);
        tetragon.getSecond().setY(4);
        tetragon.getThird().setX(4);
        tetragon.getThird().setY(4);
        tetragon.getFourth().setX(4);
        tetragon.getFourth().setY(2);
        assertEquals(CheckingTetragonAction.isSquare(tetragon), true);
    }

    @Test
    public void isSquareConcaveTetragonTest() {
        assertNotEquals(CheckingTetragonAction.isConvex(tetragon), false);
    }

    @Test
    public void isSquareRotatedTetragonTest() {
        tetragon.getFirst().setX(3);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(3);
        tetragon.getThird().setX(3);
        tetragon.getThird().setY(5);
        tetragon.getFourth().setX(5);
        tetragon.getFourth().setY(3);

        assertEquals(CheckingTetragonAction.isSquare(tetragon), true);
    }

    @Test
    public void isSquareRectangleTest() {
        tetragon.getFirst().setX(1);
        tetragon.getFirst().setY(1);
        tetragon.getSecond().setX(1);
        tetragon.getSecond().setY(2);
        tetragon.getThird().setX(10);
        tetragon.getThird().setY(2);
        tetragon.getFourth().setX(10);
        tetragon.getFourth().setY(1);

        assertEquals(CheckingTetragonAction.isSquare(tetragon), false);
    }

}