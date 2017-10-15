package com.yarachkin.tetragon.tetragonutil.converter;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;
import com.yarachkin.tetragon.tetragonutil.common.IdGenerator;
import com.yarachkin.tetragon.tetragonutil.common.StringUtil;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;

public final class TetragonConverter {

    private TetragonConverter() {

    }

    public static Tetragon convert(TetragonDto tetragonDto) throws UtilTetragonException {
        if (tetragonDto == null) {
            return null;
        }

        Point firstPoint = PointConverter.convert(tetragonDto.getFirst());
        Point secondPoint = PointConverter.convert(tetragonDto.getSecond());
        Point thirdPoint = PointConverter.convert(tetragonDto.getThird());
        Point fourthPoint = PointConverter.convert(tetragonDto.getFourth());

        return new Tetragon(IdGenerator.generateTetragonId(), firstPoint, secondPoint, thirdPoint, fourthPoint);
    }

    public static Tetragon convert(Tetragon tetragon, TetragonDto tetragonDto) {
        if (tetragonDto == null) {
            return null;
        }

        if (tetragonDto.getFirst() != null) {
            tetragon.setFirst(mergePointWithPointDto(tetragon.getFirst(), tetragonDto.getFirst()));
        }
        if (tetragonDto.getSecond() != null) {
            tetragon.setSecond(mergePointWithPointDto(tetragon.getSecond(), tetragonDto.getSecond()));
        }
        if (tetragonDto.getThird() != null) {
            tetragon.setThird(mergePointWithPointDto(tetragon.getThird(), tetragonDto.getThird()));
        }
        if (tetragonDto.getFourth() != null) {
            tetragon.setFourth(mergePointWithPointDto(tetragon.getFourth(), tetragonDto.getFourth()));
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
