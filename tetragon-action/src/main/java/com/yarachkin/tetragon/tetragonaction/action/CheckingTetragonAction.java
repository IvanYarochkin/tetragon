package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.entity.Tetragon;

import static com.yarachkin.tetragon.tetragonaction.action.CalculatingTetragonAction.calculateArea;
import static com.yarachkin.tetragon.tetragonaction.action.CalculatingTetragonAction.calculateDistanceBetweenPoints;
import static com.yarachkin.tetragon.tetragonaction.action.CalculatingTriangleAction.calculateArea;

public final class CheckingTetragonAction {

    private CheckingTetragonAction() {

    }

    public static boolean isConvex(Tetragon tetragon) {
        if ( calculateArea(tetragon.getFirst(), tetragon.getSecond(), tetragon.getThird()) > calculateArea(tetragon) ) {
            return false;
        }
        if ( calculateArea(tetragon.getFirst(), tetragon.getThird(), tetragon.getFourth()) > calculateArea(tetragon) ) {
            return false;
        }

        return calculateArea(tetragon.getSecond(), tetragon.getThird(), tetragon.getFourth()) < calculateArea(tetragon)
                && calculateArea(tetragon.getFirst(), tetragon.getSecond(), tetragon.getFourth()) < calculateArea(tetragon);
    }

    public static boolean isSquare(Tetragon tetragon) {
        double firstSide = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondSide = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdSide = calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthSide = calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        double firstDiagonal = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getThird());
        double secondDiagonal = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getFourth());

        if ( firstDiagonal != secondDiagonal ) {
            return false;
        }

        return (firstSide == secondSide) && (secondSide == thirdSide)
                && (thirdSide == fourthSide) && (fourthSide == firstSide);
    }
}
