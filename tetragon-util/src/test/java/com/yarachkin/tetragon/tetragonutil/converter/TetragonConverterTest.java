package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
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

        Point firstPoint = new Point(1, 1.0, 1.0);
        Point secondPoint = new Point(2, 2.0, 2.0);
        Point thirdPoint = new Point(3, 3.0, 1.0);
        Point fourthPoint = new Point(4, 4.0, 2.0);

        tetragon = new Tetragon(1, firstPoint, secondPoint, thirdPoint, fourthPoint);

        Point resultFirstPoint = new Point(5, 1.0, 1.0);
        Point resultSecondPoint = new Point(6, 2.0, 2.0);
        Point resultThirdPoint = new Point(7, 3.0, 1.0);
        Point resultFourthPoint = new Point(8, 4.0, 2.0);

        resultTetragon = new Tetragon(2, resultFirstPoint, resultSecondPoint, resultThirdPoint, resultFourthPoint);
    }

    @Test
    public void convertWithOneParameterTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertWithOneParameterAndPointNegativeValuesTest() throws UtilTetragonException {
        tetragonDto.getFirst().setX("-4.4");
        tetragonDto.getFirst().setY("-6.3");
        tetragon.getFirst().setX(-4.4);
        tetragon.getFirst().setY(-6.3);

        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertWithOneParameterAndPointZeroValuesTest() throws UtilTetragonException {
        tetragonDto.getFirst().setX("0");
        tetragonDto.getFirst().setY("0");
        tetragonDto.getSecond().setX("0");
        tetragonDto.getSecond().setY("0");
        tetragonDto.getThird().setX("0");
        tetragonDto.getThird().setY("0");
        tetragonDto.getFourth().setX("0");
        tetragonDto.getFourth().setY("0");

        tetragon.getFirst().setX(0);
        tetragon.getFirst().setY(0);
        tetragon.getSecond().setX(0);
        tetragon.getSecond().setY(0);
        tetragon.getThird().setX(0);
        tetragon.getThird().setY(0);
        tetragon.getFourth().setX(0);
        tetragon.getFourth().setY(0);

        assertEquals(TetragonConverter.convert(tetragonDto), tetragon);
    }

    @Test
    public void convertNullWithOneParameterTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(null), null);
    }

    @Test
    public void convertWithTwoParametersTest() throws UtilTetragonException {
        tetragonDto.getFirst().setX("22.2");
        tetragonDto.getFirst().setY("33.3");
        resultTetragon.getFirst().setX(22.2);
        resultTetragon.getFirst().setY(33.3);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullFirstPointValueTest() throws UtilTetragonException {
        tetragonDto.setFirst(null);
        tetragonDto.getSecond().setX("2.5");
        tetragonDto.getSecond().setY("4.5");
        resultTetragon.getSecond().setX(2.5);
        resultTetragon.getSecond().setY(4.5);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullSecondPointValueTest() throws UtilTetragonException {
        tetragonDto.setSecond(null);
        tetragonDto.getFirst().setX("22.2");
        tetragonDto.getFirst().setY("33.3");
        resultTetragon.getFirst().setX(22.2);
        resultTetragon.getFirst().setY(33.3);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullThirdPointValueTest() throws UtilTetragonException {
        tetragonDto.setThird(null);
        tetragonDto.getSecond().setX("7");
        tetragonDto.getSecond().setY("6");
        resultTetragon.getSecond().setX(7.0);
        resultTetragon.getSecond().setY(6.0);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullFourthPointValueTest() throws UtilTetragonException {
        tetragonDto.setFourth(null);
        tetragonDto.getSecond().setX("4.3");
        tetragonDto.getSecond().setY("4.8");
        resultTetragon.getSecond().setX(4.3);
        resultTetragon.getSecond().setY(4.8);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullXValueTest() throws UtilTetragonException {
        tetragonDto.getFourth().setX(null);
        tetragon.getFourth().setX(333);
        resultTetragon.getFourth().setX(333);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertWithTwoParametersWithNullYValueTest() throws UtilTetragonException {
        tetragonDto.getFirst().setY(null);
        tetragon.getFirst().setY(34.4);
        resultTetragon.getFirst().setY(34.4);
        assertEquals(TetragonConverter.convert(tetragon, tetragonDto), resultTetragon);
    }

    @Test
    public void convertNullWithTwoParametersTest() throws UtilTetragonException {
        assertEquals(TetragonConverter.convert(tetragon, null), null);
    }
}