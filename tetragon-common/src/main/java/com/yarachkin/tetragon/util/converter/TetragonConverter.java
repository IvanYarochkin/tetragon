package com.yarachkin.tetragon.util.converter;

import com.yarachkin.tetragon.dto.PointDto;
import com.yarachkin.tetragon.dto.TetragonDto;
import com.yarachkin.tetragon.entity.AbstractEntity;
import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;
import com.yarachkin.tetragon.exception.ModelTetragonException;
import com.yarachkin.tetragon.factory.impl.FactoryImpl;
import com.yarachkin.tetragon.util.IdGenerator;
import com.yarachkin.tetragon.util.StringUtil;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;

public final class TetragonConverter {

    private TetragonConverter() {

    }

    public static Tetragon convert(TetragonDto tetragonDto) throws UtilTetragonException {
        try {
            if ( tetragonDto == null ) {
                return null;
            }
            FactoryImpl factory = new FactoryImpl();

            Point firstPoint = PointConverter.convert(tetragonDto.getFirst());
            Point secondPoint = PointConverter.convert(tetragonDto.getSecond());
            Point thirdPoint = PointConverter.convert(tetragonDto.getThird());
            Point fourthPoint = PointConverter.convert(tetragonDto.getFourth());

            AbstractEntity abstractEntity = factory.factoryMethod("tetragon");
            Tetragon tetragon = (Tetragon) abstractEntity;

            tetragon.setId(IdGenerator.generateTetragonId());
            tetragon.setFirst(firstPoint);
            tetragon.setSecond(secondPoint);
            tetragon.setThird(thirdPoint);
            tetragon.setFourth(fourthPoint);

            return tetragon;
        } catch (ModelTetragonException e) {
            throw new UtilTetragonException(e);
        }
    }

    public static Tetragon convert(Tetragon tetragon, TetragonDto tetragonDto) {
        if ( tetragonDto == null ) {
            return null;
        }

        if ( tetragonDto.getFirst() != null ) {
            tetragon.setFirst(mergePointWithPointDto(tetragon.getFirst(), tetragonDto.getFirst()));
        }
        if ( tetragonDto.getSecond() != null ) {
            tetragon.setSecond(mergePointWithPointDto(tetragon.getSecond(), tetragonDto.getSecond()));
        }
        if ( tetragonDto.getThird() != null ) {
            tetragon.setThird(mergePointWithPointDto(tetragon.getThird(), tetragonDto.getThird()));
        }
        if ( tetragonDto.getFourth() != null ) {
            tetragon.setFourth(mergePointWithPointDto(tetragon.getFourth(), tetragonDto.getFourth()));
        }
        return tetragon;
    }

    private static Point mergePointWithPointDto(Point point, PointDto pointDto) {

        if ( pointDto.getX() != null && StringUtil.isDoubleValue(pointDto.getX()) ) {
            point.setX(Double.parseDouble(pointDto.getX()));
        }

        if ( pointDto.getY() != null && StringUtil.isDoubleValue(pointDto.getY()) ) {
            point.setY(Double.parseDouble(pointDto.getY()));
        }

        return point;
    }
}
