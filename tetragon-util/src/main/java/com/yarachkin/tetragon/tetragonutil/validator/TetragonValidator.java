package com.yarachkin.tetragon.tetragonutil.validator;

import com.yarachkin.tetragon.tetragonmodel.dto.PointDto;
import com.yarachkin.tetragon.tetragonmodel.dto.TetragonDto;
import com.yarachkin.tetragon.tetragonmodel.entity.Point;
import com.yarachkin.tetragon.tetragonutil.converter.PointConverter;
import com.yarachkin.tetragon.tetragonutil.exception.UtilTetragonException;

public class TetragonValidator {

    private TetragonValidator() {

    }

    public static boolean validate(TetragonDto tetragonDto) {
        if ((tetragonDto == null)) {
            return false;
        }

        if (!(PointValidator.validate(tetragonDto.getFirst())
                && PointValidator.validate(tetragonDto.getSecond())
                && PointValidator.validate(tetragonDto.getThird())
                && PointValidator.validate(tetragonDto.getFourth()))) {
            return false;
        }

        return validateByMoreThenTwoPointsOnLine(tetragonDto);
    }

    public static boolean validateByMoreThenTwoPointsOnLine(TetragonDto tetragonDto) {
        boolean isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirst(), tetragonDto.getSecond(), tetragonDto.getThird());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirst(), tetragonDto.getSecond(), tetragonDto.getFourth());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirst(), tetragonDto.getThird(), tetragonDto.getFourth());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getSecond(), tetragonDto.getThird(), tetragonDto.getFourth());

        return isMoreThenTwoPointsOnLine;
    }

    private static boolean checkThreePoints(PointDto firstPointDto, PointDto secondPointDto, PointDto thirdPointDto) {
        try {
            Point firstPoint = PointConverter.convert(firstPointDto);
            Point secondPoint = PointConverter.convert(secondPointDto);
            Point thirdPoint = PointConverter.convert(thirdPointDto);

            double firstX = firstPoint.getX();
            double secondX = secondPoint.getX();
            double thirdX = thirdPoint.getX();
            double firstY = firstPoint.getY();
            double secondY = secondPoint.getY();
            double thirdY = thirdPoint.getY();

            if ((firstX - secondX) * (thirdY - secondY) == (firstY - secondY) * (thirdX - secondX)) {
                return false;
            }

            if ((secondX - firstX) * (thirdY - firstY) == (secondY - firstY) * (thirdX - firstX)) {
                return false;
            }

            return ((thirdX - firstX) * (secondY - firstY) != (thirdY - firstY) * (secondX - firstX));

        } catch (UtilTetragonException e) {
            return false;
        }
    }
}
