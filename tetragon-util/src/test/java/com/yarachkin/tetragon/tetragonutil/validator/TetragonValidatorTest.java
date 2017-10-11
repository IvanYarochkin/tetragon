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
        PointDto firstPointDto = new PointDto("1.0", "1.0");
        PointDto secondPointDto = new PointDto("2.0", "2.0");
        PointDto thirdPointDto = new PointDto("3.0", "1.0");
        PointDto fourthPointDto = new PointDto("4.0", "2.0");

        tetragonDto = new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);

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
        tetragonDto.getFirstPoint().setX("1.f3");
        tetragonDto.getFirstPoint().setY("d2.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectSecondPointValuesTest() {
        tetragonDto.getSecondPoint().setX("a.f3");
        tetragonDto.getSecondPoint().setY("2z.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectThirdPointValuesTest() {
        tetragonDto.getThirdPoint().setX("1a.f3");
        tetragonDto.getThirdPoint().setY("23z.43");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateIncorrectFourthPointValuesTest() {
        tetragonDto.getFourthPoint().setX("a33.f3");
        tetragonDto.getFourthPoint().setY("2z3.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateSamePointValuesTest() {
        tetragonDto.getFourthPoint().setX("a33.f3");
        tetragonDto.getFourthPoint().setY("2z3.4");
        tetragonDto.getSecondPoint().setX("a33.f3");
        tetragonDto.getSecondPoint().setY("2z3.4");
        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateAllSamePointValuesTest() {
        tetragonDto.getFirstPoint().setX("a33.f3");
        tetragonDto.getFirstPoint().setY("2z3.4");
        tetragonDto.getSecondPoint().setX("a33.f3");
        tetragonDto.getSecondPoint().setY("2z3.4");
        tetragonDto.getThirdPoint().setX("a33.f3");
        tetragonDto.getThirdPoint().setY("2z3.4");
        tetragonDto.getFourthPoint().setX("a33.f3");
        tetragonDto.getFourthPoint().setY("2z3.4");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnLineTest() {
        tetragonDto.getFirstPoint().setX("1");
        tetragonDto.getFirstPoint().setY("1");
        tetragonDto.getSecondPoint().setX("4");
        tetragonDto.getSecondPoint().setY("4");
        tetragonDto.getThirdPoint().setX("3");
        tetragonDto.getThirdPoint().setY("3");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnOXTest() {
        tetragonDto.getSecondPoint().setX("4");
        tetragonDto.getSecondPoint().setY("0");
        tetragonDto.getThirdPoint().setX("3");
        tetragonDto.getThirdPoint().setY("0");
        tetragonDto.getFourthPoint().setX("1");
        tetragonDto.getFourthPoint().setY("0");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnOYTest() {
        tetragonDto.getFirstPoint().setX("0");
        tetragonDto.getFirstPoint().setY("2");
        tetragonDto.getSecondPoint().setX("0");
        tetragonDto.getSecondPoint().setY("3");
        tetragonDto.getFourthPoint().setX("0");
        tetragonDto.getFourthPoint().setY("8.2");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateThreePointsOnLineWithIncorrectPointValuesTest() {
        tetragonDto.getFirstPoint().setX("1.2d");
        tetragonDto.getFirstPoint().setY("d2.3");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }

    @Test
    public void validateFourPointsOnLineTest() {
        tetragonDto.getFirstPoint().setX("1");
        tetragonDto.getFirstPoint().setY("1");
        tetragonDto.getSecondPoint().setX("4");
        tetragonDto.getSecondPoint().setY("4");
        tetragonDto.getThirdPoint().setX("3");
        tetragonDto.getThirdPoint().setY("3");
        tetragonDto.getFourthPoint().setX("2");
        tetragonDto.getFourthPoint().setY("2");

        assertEquals(TetragonValidator.validate(tetragonDto), false);
    }
}