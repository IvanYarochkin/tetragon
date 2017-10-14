package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.tetragonmodel.entity.Tetragon;

public class CheckingTetragonAction {

    private CheckingTetragonAction(){

    }

    public static boolean isConvex(Tetragon tetragon) {
        if (CalculatingTriangleAction.calculateArea(tetragon.getFirst(), tetragon.getSecond(), tetragon.getThird())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        if (CalculatingTriangleAction.calculateArea(tetragon.getFirst(), tetragon.getThird(), tetragon.getFourth())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        if (CalculatingTriangleAction.calculateArea(tetragon.getSecond(), tetragon.getThird(), tetragon.getFourth())
                > CalculatingTetragonAction.calculateArea(tetragon)) {
            return false;
        }
        return CalculatingTriangleAction.calculateArea(tetragon.getFirst(), tetragon.getSecond(), tetragon.getFourth())
                < CalculatingTetragonAction.calculateArea(tetragon);
    }

    public static boolean isSquare(Tetragon tetragon){
        double firstSide = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondSide = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdSide = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthSide = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        double firstDiagonal = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getThird());
        double secondDiagonal = CalculatingTetragonAction.calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getFourth());


        if (firstDiagonal != secondDiagonal){
            return false;
        }

        return (firstSide == secondSide) && (secondSide == thirdSide) && (thirdSide == fourthSide) && (fourthSide == firstSide);
    }
}
