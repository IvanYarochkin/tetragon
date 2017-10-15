package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointConverterTest {
    private PointDto pointDto;
    private Point point;

    @BeforeMethod
    public void setUp() {
        pointDto = new PointDto("1.2", "2.3");
        point = new Point(1, 1.2, 2.3);
    }

    @Test
    public void convertTest() throws UtilTetragonException {
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertZeroValueTest() throws UtilTetragonException {
        pointDto.setX("0.0");
        pointDto.setY("0.0");
        point.setX(0.0);
        point.setY(0.0);
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertNegativeValueTest() throws UtilTetragonException {
        pointDto.setX("-1.5");
        pointDto.setY("-2.6");
        point.setX(-1.5);
        point.setY(-2.6);
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertIntegerValueTest() throws UtilTetragonException {
        pointDto.setX("5");
        pointDto.setY("7");
        point.setX(5.0);
        point.setY(7.0);
        assertEquals(PointConverter.convert(pointDto), point);
    }

    @Test
    public void convertNulValueTest() throws UtilTetragonException {
        assertEquals(PointConverter.convert(null), null);
    }

    @Test(expectedExceptions = UtilTetragonException.class)
    public void convertIncorrectXValueTest() throws UtilTetragonException {
        pointDto.setX("1.2d2");
        pointDto.setY("7");
        PointConverter.convert(pointDto);
    }

    @Test(expectedExceptions = UtilTetragonException.class)
    public void convertIncorrectYValueTest() throws UtilTetragonException {
        pointDto.setX("1");
        pointDto.setY("3g.4d");
        PointConverter.convert(pointDto);
    }

}