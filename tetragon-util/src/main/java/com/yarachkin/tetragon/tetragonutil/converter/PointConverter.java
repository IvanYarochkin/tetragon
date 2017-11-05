package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.entity.AbstractEntity;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.exception.ModelTetragonException;
import com.yarachkin.tetragon.tetragonmodel.factory.impl.FactoryImpl;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;
import com.yarachkin.tetragon.tetragonutil.validator.PointValidator;

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
        } catch (ModelTetragonException e) {
            throw new UtilTetragonException(e);
        }
    }
}
