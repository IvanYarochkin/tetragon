package com.yarachkin.tetragon.util;

import com.yarachkin.tetragon.dto.PointDto;
import com.yarachkin.tetragon.dto.TetragonDto;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.util.converter.TetragonConverter;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;
import com.yarachkin.tetragon.util.validator.TetragonValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public final class LineParser {

    private static final Logger LOGGER = LogManager.getLogger();

    private LineParser() {

    }

    public static List<Tetragon> parse(List<String> lines, String pattern) {
        List<Tetragon> tetragons = new ArrayList<>();
        for (String line : lines) {
            try {
                TetragonDto tetragonDto = parseLine(line, pattern);
                if ( TetragonValidator.validate(tetragonDto) ) {
                    tetragons.add(TetragonConverter.convert(tetragonDto));
                }
            } catch (UtilTetragonException e) {
                LOGGER.log(Level.INFO, e.getMessage());
            }
        }
        return tetragons;
    }

    public static TetragonDto parseLine(String line, String pattern) throws UtilTetragonException {
        String[] splittedLine = line.split(pattern);

        if ( splittedLine.length != 8 ) {
            throw new UtilTetragonException("Line hasn't 8 parameters, parameters = " + splittedLine.length);
        }

        PointDto firstPointDto = new PointDto(splittedLine[0], splittedLine[1]);
        PointDto secondPointDto = new PointDto(splittedLine[2], splittedLine[3]);
        PointDto thirdPointDto = new PointDto(splittedLine[4], splittedLine[5]);
        PointDto fourthPointDto = new PointDto(splittedLine[6], splittedLine[7]);

        return new TetragonDto(firstPointDto, secondPointDto, thirdPointDto, fourthPointDto);
    }
}
