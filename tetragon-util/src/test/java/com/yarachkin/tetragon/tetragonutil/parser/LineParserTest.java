package com.yarachkin.tetragon.tetragonutil.parser;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LineParserTest {
    private TetragonDto tetragonDto;
    private String pattern;

    @BeforeMethod
    public void setUp() {
        pattern = "[ ]";

        PointDto firstPointDto = new PointDto("1.3", "2.2");
        PointDto secondPointDto = new PointDto("3.3", "4.4");
        PointDto thirdPointDto = new PointDto("4.4", "3.5");
        PointDto fourthPointDto = new PointDto("8.4", "5.5");

        tetragonDto = new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);
    }

    @Test
    public void parseTest() throws UtilTetragonException {
        assertEquals(LineParser.parse("1.3 2.2 3.3 4.4 4.4 3.5 8.4 5.5", pattern), tetragonDto);
    }

    @Test
    public void parseWithStringParametersTest() throws UtilTetragonException {
        tetragonDto.getFirstPoint().setY("2.3d");
        assertEquals(LineParser.parse("1.3 2.3d 3.3 4.4 4.4 3.5 8.4 5.5", pattern), tetragonDto);
    }

    @Test(expectedExceptions = UtilTetragonException.class)
    public void parseWithLessParametersTest() throws UtilTetragonException {;
        LineParser.parse("1.0 2.4 22", pattern);
    }

    @Test(expectedExceptions = UtilTetragonException.class)
    public void parseWithMoreParametersTest() throws UtilTetragonException {;
        LineParser.parse("1.0 2.4 22 2 3 44 33 22 11 22", pattern);
    }
}