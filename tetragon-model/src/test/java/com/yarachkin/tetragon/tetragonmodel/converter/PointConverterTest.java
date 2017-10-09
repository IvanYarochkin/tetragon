package com.yarachkin.tetragon.tetragonmodel.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointConverterTest {
    private PointDto pointDto;
    private Point point;

    @BeforeMethod
    public void setUp() {
        pointDto = new PointDto("1.2", "2.3");
        point = new Point(1.2, 2.3);
    }

    @Test
    public void convertTest() {
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertZeroValueTest() {
        pointDto.setX("0.0");
        pointDto.setY("0.0");
        point.setX(0.0);
        point.setY(0.0);
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertNegativeValueTest() {
        pointDto.setX("-1.5");
        pointDto.setY("-2.6");
        point.setX(-1.5);
        point.setY(-2.6);
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertIntegerValueTest() {
        pointDto.setX("5");
        pointDto.setY("7");
        point.setX(5.0);
        point.setY(7.0);
        assertEquals(PointConverter.convert(pointDto), point);
    }
}