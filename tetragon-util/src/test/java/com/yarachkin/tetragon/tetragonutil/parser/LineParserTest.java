package com.yarachkin.tetragon.tetragonutil.parser;

import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class LineParserTest {
    List<String> lines;
    List<Tetragon> tetragons;
    private String pattern;

    @BeforeMethod
    public void setUp() {
        pattern = "\\s";

        lines = new ArrayList<>();
        lines.add("1.3 2.2 3.3 4.4 4.4 3.5 8.4 5.5");
        lines.add("3.2 2.2 1.45 34 4 12 2 33");

        tetragons = new ArrayList<>();

        Tetragon firstTetragon = new Tetragon(1, new Point(1.3, 2.2), new Point(3.3, 4.4),
                new Point(4.4, 3.5), new Point(8.4, 5.5));

        Tetragon secondTetragon = new Tetragon(2, new Point(3.2, 2.2), new Point(1.45, 34),
                new Point(4, 12), new Point(2, 33));

        tetragons.add(firstTetragon);
        tetragons.add(secondTetragon);
    }

    @Test
    public void parseTest() throws UtilTetragonException {
        assertEquals(LineParser.parse(lines, pattern), tetragons);
    }

    @Test
    public void parseWithStringParametersTest() throws UtilTetragonException {
        lines.add("1.3 2.3d 3.3 4.4 4.4 3.5 8.4 5.5");
        assertEquals(LineParser.parse(lines, pattern), tetragons);
    }

    @Test
    public void parseWithLessParametersTest() throws UtilTetragonException {
        lines.removeAll(lines);
        lines.add("1.0 2.4 22");
        assertEquals(LineParser.parse(lines, pattern), new ArrayList<String>());
    }

    @Test
    public void parseWithMoreParametersTest() throws UtilTetragonException {
        lines.removeAll(lines);
        lines.add("1.0 2.4 22 2 3 44 33 22 11 22");
        LineParser.parse(lines, pattern);
        assertEquals(LineParser.parse(lines, pattern), new ArrayList<String>());
    }
}