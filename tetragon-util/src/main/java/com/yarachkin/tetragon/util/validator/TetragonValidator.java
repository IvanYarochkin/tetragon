package com.yarachkin.tetragon.util.validator;

import com.yarachkin.tetragon.model.dto.PointDto;
import com.yarachkin.tetragon.model.dto.TetragonDto;
import com.yarachkin.tetragon.model.entity.Point;
import com.yarachkin.tetragon.util.converter.PointConverter;
import com.yarachkin.tetragon.util.exception.UtilTetragonException;

public class TetragonValidator {

    private TetragonValidator() {

    }

    public static boolean validate(TetragonDto tetragonDto) {
        if ((tetragonDto == null)) {
            return false;
        }

        if (!(PointValidator.validate(tetragonDto.getFirstPoint())
                && PointValidator.validate(tetragonDto.getSecondPoint())
                && PointValidator.validate(tetragonDto.getThirdPoint())
                && PointValidator.validate(tetragonDto.getFourthPoint()))) {
            return false;
        }

        return validateByMoreThenTwoPointsOnLine(tetragonDto);
    }

    public static boolean validateByMoreThenTwoPointsOnLine(TetragonDto tetragonDto) {
        boolean isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirstPoint(), tetragonDto.getSecondPoint(), tetragonDto.getThirdPoint());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirstPoint(), tetragonDto.getSecondPoint(), tetragonDto.getFourthPoint());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getFirstPoint(), tetragonDto.getThirdPoint(), tetragonDto.getFourthPoint());
        if (!isMoreThenTwoPointsOnLine) {
            return false;
        }

        isMoreThenTwoPointsOnLine = checkThreePoints(tetragonDto.getSecondPoint(), tetragonDto.getThirdPoint(), tetragonDto.getFourthPoint());

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
