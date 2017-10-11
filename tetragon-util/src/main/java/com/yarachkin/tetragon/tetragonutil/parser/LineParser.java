package com.yarachkin.tetragon.tetragonutil.parser;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;

public class LineParser {

    public static TetragonDto parse(String line) throws UtilTetragonException {
        String[] splittedLine = line.split("[ ]");

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
