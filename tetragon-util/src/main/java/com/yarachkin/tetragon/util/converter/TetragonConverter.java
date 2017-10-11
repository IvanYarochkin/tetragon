package com.yarachkin.tetragon.util.converter;

import com.yarachkin.tetragon.model.dto.TetragonDto;
import com.yarachkin.tetragon.model.entity.Point;
import com.yarachkin.tetragon.model.entity.Tetragon;
import com.yarachkin.tetragon.util.common.IdGenerator;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;

public class TetragonConverter {

    private TetragonConverter() {

    }

    public static Tetragon convert(TetragonDto tetragonDto) throws UtilTetragonException {
        if (tetragonDto == null) {
            return null;
        }

        Point firstPoint = PointConverter.convert(tetragonDto.getFirstPoint());
        Point secondPoint = PointConverter.convert(tetragonDto.getSecondPoint());
        Point thirdPoint = PointConverter.convert(tetragonDto.getThirdPoint());
        Point fourthPoint = PointConverter.convert(tetragonDto.getFourthPoint());

        return new Tetragon(IdGenerator.generateId(), firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public static Tetragon convert(Tetragon tetragon, TetragonDto tetragonDto) throws UtilTetragonException {
        if (tetragonDto == null) {
            return null;
        }

        if (tetragonDto.getFirstPoint() != null) {
            tetragon.setFirstPoint(PointConverter.convert(tetragonDto.getFirstPoint()));
        }
        if (tetragonDto.getSecondPoint() != null) {
            tetragon.setSecondPoint(PointConverter.convert(tetragonDto.getSecondPoint()));
        }
        if (tetragonDto.getThirdPoint() != null) {
            tetragon.setThirdPoint(PointConverter.convert(tetragonDto.getThirdPoint()));
        }
        if (tetragonDto.getFourthPoint() != null) {
            tetragon.setFourthPoint(PointConverter.convert(tetragonDto.getFourthPoint()));
        }
        return tetragon;
    }
}
