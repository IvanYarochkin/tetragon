package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PointValidatorTest {
    private PointDto pointDto;

    @BeforeMethod
    public void setUp() {
        pointDto = new PointDto("1", "2");
    }

    @Test
    public void isDoubleValuesTest() {
        assertEquals(PointValidator.validate(pointDto), true);
    }

    @Test
    public void isDoubleValuesWithIncorrectFirstValueTest() {
        pointDto.setX("test");
        assertEquals(PointValidator.validate(pointDto), false);
    }

    @Test
    public void isDoubleValuesWithIncorrectSecondValueTest() {
        pointDto.setY("test");
        assertEquals(PointValidator.validate(pointDto), false);
    }

    @Test
    public void IsDoubleWithIntegerValuesTest() {
        pointDto.setX("1");
        pointDto.setY("2");
        assertEquals(PointValidator.validate(pointDto), true);
    }

    @Test
    public void IsDoubleWithNegativeValuesTest() {
        pointDto.setX("-1");
        pointDto.setY("-24.2");
        assertEquals(PointValidator.validate(pointDto), true);
    }

    @Test
    public void IsDoubleWithNullValueTest() {
        assertEquals(PointValidator.validate(null), false);
    }

}