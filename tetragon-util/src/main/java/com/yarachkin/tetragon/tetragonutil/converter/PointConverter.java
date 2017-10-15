package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import com.yarachkin.tetragon.tetragonutil.validator.PointValidator;

public final class PointConverter {

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

        return new Point(IdGenerator.generatePointId(), x, y);

    }
}
