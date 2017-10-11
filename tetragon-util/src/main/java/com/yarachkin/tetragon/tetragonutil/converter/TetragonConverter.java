package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
import com.yarachkin.tetragon.tetragonutil.common.StringUtil;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;

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
            tetragon.setFirstPoint(mergePointWithPointDto(tetragon.getFirstPoint(), tetragonDto.getFirstPoint()));
        }
        if (tetragonDto.getSecondPoint() != null) {
            tetragon.setSecondPoint(mergePointWithPointDto(tetragon.getSecondPoint(), tetragonDto.getSecondPoint()));
        }
        if (tetragonDto.getThirdPoint() != null) {
            tetragon.setThirdPoint(mergePointWithPointDto(tetragon.getThirdPoint(), tetragonDto.getThirdPoint()));
        }
        if (tetragonDto.getFourthPoint() != null) {
            tetragon.setFourthPoint(mergePointWithPointDto(tetragon.getFourthPoint(), tetragonDto.getFourthPoint()));
        }
        return tetragon;
    }

    private static Point mergePointWithPointDto(Point point, PointDto pointDto) {
        if (pointDto.getX() != null && StringUtil.isDoubleValue(pointDto.getX())) {
            point.setX(Double.parseDouble(pointDto.getX()));
        }
        if (pointDto.getY() != null && StringUtil.isDoubleValue(pointDto.getY())) {
            point.setY(Double.parseDouble(pointDto.getY()));
        }

        return point;
    }
}
