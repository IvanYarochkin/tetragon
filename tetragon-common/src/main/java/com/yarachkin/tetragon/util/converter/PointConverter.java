package com.yarachkin.tetragon.util.converter;

import com.yarachkin.tetragon.dto.PointDto;
import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.exception.CommonTetragonException;
import com.yarachkin.tetragon.factory.impl.FactoryImpl;
import com.yarachkin.tetragon.util.IdGenerator;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;
import com.yarachkin.tetragon.util.validator.PointValidator;

public final class PointConverter {

    private PointConverter() {

    }

    public static Point convert(PointDto pointDto) throws UtilTetragonException {
        try {
            if ( pointDto == null ) {
                return null;
            }
            if ( !PointValidator.validate(pointDto) ) {
                throw new UtilTetragonException("Incorrect values x = " + pointDto.getX() + " or y = " + pointDto.getY());
            }
            double x = Double.parseDouble(pointDto.getX());
            double y = Double.parseDouble(pointDto.getY());

            FactoryImpl factory = new FactoryImpl();
            AbstractEntity abstractEntity = factory.factoryMethod("point");

            Point point = (Point) abstractEntity;
            point.setId(IdGenerator.generatePointId());
            point.setX(x);
            point.setY(y);

            return point;
        } catch (CommonTetragonException e) {
            throw new UtilTetragonException(e);
        }
    }
}
