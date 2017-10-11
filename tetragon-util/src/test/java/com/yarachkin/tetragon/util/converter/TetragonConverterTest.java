package com.yarachkin.tetragon.util.converter;

import com.yarachkin.tetragon.model.dto.PointDto;
import com.yarachkin.tetragon.model.dto.TetragonDto;
import com.yarachkin.tetragon.model.entity.Point;
import com.yarachkin.tetragon.model.entity.Tetragon;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TetragonConverterTest {
    private TetragonDto tetragonDto;
    private Tetragon tetragon;
    private Tetragon resultTetragon;

    @BeforeMethod
    public void setUp() {
        PointDto firstPointDto = new PointDto("1.0", "1.0");
        PointDto secondPointDto = new PointDto("2.0", "2.0");
        PointDto thirdPointDto = new PointDto("3.0", "1.0");
        PointDto fourthPointDto = new PointDto("4.0", "2.0");

        tetragonDto = new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);

        Point firstPoint = new Point(1.0, 1.0);
        Point secondPoint = new Point(2.0, 2.0);
        Point thirdPoint = new Point(3.0, 1.0);
        Point fourthPoint = new Point(4.0, 2.0);

        tetragon = new Tetragon(1, firstPoint, secondPoint, thirdPoint, fourthPoint);

        Point resultFirstPoint = new Point(1.0, 1.0);
        Point resultSecondPoint = new Point(2.0, 2.0);
        Point resultThirdPoint = new Point(3.0, 1.0);
        Point resultFourthPoint = new Point(4.0, 2.0);

        resultTetragon = new Tetragon(1, resultFirstPoint, resultSecondPoint, resultThirdPoint, resultFourthPoint);
    }

    @Test
    public void convertWithOneParameterTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertWithOneParameterAndPointNegativeValuesTest() throws UtilTetragonException {
        tetragonDto.getFirstPoint().setX("-4.4");
        tetragonDto.getFirstPoint().setY("-6.3");
        tetragon.getFirstPoint().setX(-4.4);
        tetragon.getFirstPoint().setY(-6.3);

        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertWithOneParameterAndPointZeroValuesTest() throws UtilTetragonException {
        tetragonDto.getFirstPoint().setX("0");
        tetragonDto.getFirstPoint().setY("0");
        tetragonDto.getSecondPoint().setX("0");
        tetragonDto.getSecondPoint().setY("0");
        tetragonDto.getThirdPoint().setX("0");
        tetragonDto.getThirdPoint().setY("0");
        tetragonDto.getFourthPoint().setX("0");
        tetragonDto.getFourthPoint().setY("0");

        tetragon.getFirstPoint().setX(0);
        tetragon.getFirstPoint().setY(0);
        tetragon.getSecondPoint().setX(0);
        tetragon.getSecondPoint().setY(0);
        tetragon.getThirdPoint().setX(0);
        tetragon.getThirdPoint().setY(0);
        tetragon.getFourthPoint().setX(0);
        tetragon.getFourthPoint().setY(0);

        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertNullWithOneParameterTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(null), null);
    }

    @Test
    public void convertWithTwoParametersTest() throws UtilTetragonException {
        tetragonDto.getFirstPoint().setX("22.2");
        tetragonDto.getFirstPoint().setY("33.3");
        resultTetragon.getFirstPoint().setX(22.2);
        resultTetragon.getFirstPoint().setY(33.3);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullFirstPointValueTest() throws UtilTetragonException {
        tetragonDto.setFirstPoint(null);
        tetragonDto.getSecondPoint().setX("2.5");
        tetragonDto.getSecondPoint().setY("4.5");
        resultTetragon.getSecondPoint().setX(2.5);
        resultTetragon.getSecondPoint().setY(4.5);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullSecondPointValueTest() throws UtilTetragonException {
        tetragonDto.setSecondPoint(null);
        tetragonDto.getFirstPoint().setX("22.2");
        tetragonDto.getFirstPoint().setY("33.3");
        resultTetragon.getFirstPoint().setX(22.2);
        resultTetragon.getFirstPoint().setY(33.3);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullThirdPointValueTest() throws UtilTetragonException {
        tetragonDto.setThirdPoint(null);
        tetragonDto.getSecondPoint().setX("7");
        tetragonDto.getSecondPoint().setY("6");
        resultTetragon.getSecondPoint().setX(7.0);
        resultTetragon.getSecondPoint().setY(6.0);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullFourthPointValueTest() throws UtilTetragonException {
        tetragonDto.setFourthPoint(null);
        tetragonDto.getSecondPoint().setX("4.3");
        tetragonDto.getSecondPoint().setY("4.8");
        resultTetragon.getSecondPoint().setX(4.3);
        resultTetragon.getSecondPoint().setY(4.8);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertNullWithTwoParametersTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(tetragon, null), null);
    }
}