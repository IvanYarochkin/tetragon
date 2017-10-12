package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TetragonValidatorTest {
    private TetragonDto tetragonDto;

    @BeforeMethod
    public void setUp() {
        PointDto first = new PointDto("1.0", "1.0");
        PointDto second = new PointDto("2.0", "2.0");
        PointDto third = new PointDto("3.0", "1.0");
        PointDto fourth = new PointDto("4.0", "2.0");

        tetragonDto = new TetragonDto(first, second, third, fourth);

    }

    @Test
    public void validateTest() {
        assertEquals(TetragonValidator.validate(tetragonDto), true);
    }

    @Test
    public void validateNullValueTest() {
        assertEquals(TetragonValidator.validate(null), false);
    }

    @Test
    public void validateIncorrectFirstPointValuesTest() {
        tetragonDto.getFirst().setX("1.f3");
        tetragonDto.getFirst().setY("d2.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectSecondPointValuesTest() {
        tetragonDto.getSecond().setX("a.f3");
        tetragonDto.getSecond().setY("2z.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectThirdPointValuesTest() {
        tetragonDto.getThird().setX("1a.f3");
        tetragonDto.getThird().setY("23z.43");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectFourthPointValuesTest() {
        tetragonDto.getFourth().setX("a33.f3");
        tetragonDto.getFourth().setY("2z3.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateSamePointValuesTest() {
        tetragonDto.getFourth().setX("a33.f3");
        tetragonDto.getFourth().setY("2z3.4");
        tetragonDto.getSecond().setX("a33.f3");
        tetragonDto.getSecond().setY("2z3.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateAllSamePointValuesTest() {
        tetragonDto.getFirst().setX("a33.f3");
        tetragonDto.getFirst().setY("2z3.4");
        tetragonDto.getSecond().setX("a33.f3");
        tetragonDto.getSecond().setY("2z3.4");
        tetragonDto.getThird().setX("a33.f3");
        tetragonDto.getThird().setY("2z3.4");
        tetragonDto.getFourth().setX("a33.f3");
        tetragonDto.getFourth().setY("2z3.4");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnLineTest() {
        tetragonDto.getFirst().setX("1");
        tetragonDto.getFirst().setY("1");
        tetragonDto.getSecond().setX("4");
        tetragonDto.getSecond().setY("4");
        tetragonDto.getThird().setX("3");
        tetragonDto.getThird().setY("3");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnOXTest() {
        tetragonDto.getSecond().setX("4");
        tetragonDto.getSecond().setY("0");
        tetragonDto.getThird().setX("3");
        tetragonDto.getThird().setY("0");
        tetragonDto.getFourth().setX("1");
        tetragonDto.getFourth().setY("0");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnOYTest() {
        tetragonDto.getFirst().setX("0");
        tetragonDto.getFirst().setY("2");
        tetragonDto.getSecond().setX("0");
        tetragonDto.getSecond().setY("3");
        tetragonDto.getFourth().setX("0");
        tetragonDto.getFourth().setY("8.2");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnLineWithIncorrectPointValuesTest() {
        tetragonDto.getFirst().setX("1.2d");
        tetragonDto.getFirst().setY("d2.3");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateFourPointsOnLineTest() {
        tetragonDto.getFirst().setX("1");
        tetragonDto.getFirst().setY("1");
        tetragonDto.getSecond().setX("4");
        tetragonDto.getSecond().setY("4");
        tetragonDto.getThird().setX("3");
        tetragonDto.getThird().setY("3");
        tetragonDto.getFourth().setX("2");
        tetragonDto.getFourth().setY("2");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }
}