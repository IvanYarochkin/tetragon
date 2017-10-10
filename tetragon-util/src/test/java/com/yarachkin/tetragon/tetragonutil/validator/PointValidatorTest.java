package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointValidatorTest {
    PointDto pointDto;

    @BeforeMethod
    public void setUp() throws Exception {
        pointDto = new PointDto("1", "2");
    }

    @Test
    public void isDoubleValuesTest() throws Exception {
        assertEquals(PointValidator.isDoubleValues(pointDto), true);
    }

    @Test
    public void isDoubleValuesWithIncorrectFirstValueTest() throws Exception {
        pointDto.setX("test");
        assertEquals(PointValidator.isDoubleValues(pointDto), false);
    }

    @Test
    public void isDoubleValuesWithIncorrectSecondValueTest() throws Exception {
        pointDto.setY("test");
        assertEquals(PointValidator.isDoubleValues(pointDto), false);
    }

    @Test
    public void IsDoubleWithIntegerValuesTest() throws Exception {
        pointDto.setX("1");
        pointDto.setY("2");
        assertEquals(PointValidator.isDoubleValues(pointDto), true);
    }

    @Test
    public void IsDoubleWithNullValueTest() throws Exception {
        assertEquals(PointValidator.isDoubleValues(null), false);
    }

}