package com.yarachkin.tetragon.util.parser;

import com.yarachkin.tetragon.model.dto.PointDto;
import com.yarachkin.tetragon.model.dto.TetragonDto;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;

public class LineParser {

    private LineParser() {

    }

    public static TetragonDto parse(String line, String pattern) throws UtilTetragonException {
        String[] splittedLine = line.split(pattern);

        if (splittedLine.length != 8) {
            throw new UtilTetragonException("Line hasn't 8 parameters, parameters = " + splittedLine.length);
        }

        PointDto firstPointDto = new PointDto(splittedLine[0], splittedLine[1]);
        PointDto secondPointDto = new PointDto(splittedLine[2], splittedLine[3]);
        PointDto thirdPointDto = new PointDto(splittedLine[4], splittedLine[5]);
        PointDto fourthPointDto = new PointDto(splittedLine[6], splittedLine[7]);

        return new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);
    }
}
