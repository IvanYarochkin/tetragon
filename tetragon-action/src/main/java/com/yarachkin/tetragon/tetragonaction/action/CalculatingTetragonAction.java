package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.entity.Point;
import com.yarachkin.tetragon.entity.Tetragon;

import static java.lang.Math.hypot;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public final class CalculatingTetragonAction {

    private CalculatingTetragonAction() {

    }

    public static double calculatePerimeter(Tetragon tetragon) {
        double firstDistance = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondDistance = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdDistance = calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthDistance = calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        return firstDistance + secondDistance + thirdDistance + fourthDistance;
    }

    public static double calculateArea(Tetragon tetragon) {
        double firstDistance = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getSecond());
        double secondDistance = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getThird());
        double thirdDistance = calculateDistanceBetweenPoints(tetragon.getThird(), tetragon.getFourth());
        double fourthDistance = calculateDistanceBetweenPoints(tetragon.getFourth(), tetragon.getFirst());

        double firstDiagonal = calculateDistanceBetweenPoints(tetragon.getFirst(), tetragon.getThird());
        double secondDiagonal = calculateDistanceBetweenPoints(tetragon.getSecond(), tetragon.getFourth());

        return 0.25 * sqrt(4 * pow(firstDiagonal, 2) * pow(secondDiagonal, 2) - pow(pow(secondDistance, 2)
                + pow(fourthDistance, 2) - pow(firstDistance, 2) - pow(thirdDistance, 2), 2));
    }

    public static double calculateDistanceBetweenPoints(Point first, Point second) {
        double oxDistant = first.getX() - second.getX();
        double oyDistant = first.getY() - second.getY();

        return hypot(oxDistant, oyDistant);
    }
}
