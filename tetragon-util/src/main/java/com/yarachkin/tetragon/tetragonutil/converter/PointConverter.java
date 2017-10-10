package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonutil.exception.UtilException;
import com.yarachkin.tetragon.tetragonutil.validator.PointValidator;

public class PointConverter {

    private PointConverter() {

    }

    public static Point convert(PointDto pointDto) throws UtilException {
        if (pointDto == null) {
            return null;
        }
        if (!PointValidator.isDoubleValues(pointDto)) {
            throw new UtilException("Incorrect values x = " + pointDto.getX() + " or y = " + pointDto.getY());
        }
        double x = Double.parseDouble(pointDto.getX());
        double y = Double.parseDouble(pointDto.getY());

        return new Point(x, y);

    }
}
