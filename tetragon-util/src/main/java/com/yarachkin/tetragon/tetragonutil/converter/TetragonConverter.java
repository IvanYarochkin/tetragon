package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.exception.UtilException;
import com.yarachkin.tetragon.tetragonutil.idgenerator.IdGenerator;

public class TetragonConverter {

    private TetragonConverter(){

    }

    public static Tetragon convert(TetragonDto tetragonDto) throws UtilException {
        if (tetragonDto == null) {
            return null;
        }
        Point firstPoint = PointConverter.convert(tetragonDto.getFirstPoint());
        Point secondPoint = PointConverter.convert(tetragonDto.getSecondPoint());
        Point thirdPoint = PointConverter.convert(tetragonDto.getThirdPoint());
        Point fourthPoint = PointConverter.convert(tetragonDto.getFourthPoint());

        return new Tetragon(IdGenerator.generateId(), firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public static Tetragon convert(Tetragon tetragon, TetragonDto tetragonDto) throws UtilException {
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
