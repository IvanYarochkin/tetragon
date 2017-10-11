package com.yarachkin.tetragon.util.converter;

import com.yarachkin.tetragon.model.dto.PointDto;
import com.yarachkin.tetragon.model.entity.Point;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;
import com.yarachkin.tetragon.util.validator.PointValidator;

public class PointConverter {

    private PointConverter() {

    }

    public static Point convert(PointDto pointDto) throws UtilTetragonException {
        if (pointDto == null) {
            return null;
        }
        if (!PointValidator.validate(pointDto)) {
            throw new UtilTetragonException("Incorrect values x = " + pointDto.getX() + " or y = " + pointDto.getY());
        }
        double x = Double.parseDouble(pointDto.getX());
        double y = Double.parseDouble(pointDto.getY());

        return new Point(x, y);

    }
}
