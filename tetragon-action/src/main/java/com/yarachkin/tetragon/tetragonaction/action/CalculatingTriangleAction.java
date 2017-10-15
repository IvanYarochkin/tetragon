package com.yarachkin.tetragon.tetragonaction.action;

import com.yarachkin.tetragon.tetragonmodel.entity.Point;

import static java.lang.Math.sqrt;

public final class CalculatingTriangleAction {

    private CalculatingTriangleAction() {

    }

    public static double calculateArea(Point first, Point second, Point third) {
        double firstDistance = CalculatingTetragonAction.calculateDistanceBetweenPoints(first, second);
        double secondDistance = CalculatingTetragonAction.calculateDistanceBetweenPoints(second, third);
        double thirdDistance = CalculatingTetragonAction.calculateDistanceBetweenPoints(third, first);

        double halfPerimeter = (firstDistance + secondDistance + thirdDistance) / 2;

        return sqrt(halfPerimeter * (halfPerimeter - firstDistance) * (halfPerimeter - secondDistance)
                * (halfPerimeter - thirdDistance));
    }
}
